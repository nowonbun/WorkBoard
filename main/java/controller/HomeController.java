package controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import bean.JoinBean;
import common.AbstractController;
import common.LocalPaths;
import common.MailManager;
import common.PropertyMap;
import common.SessionName;
import common.Util;
import dao.CompanyDao;
import dao.StateDao;
import dao.UserDao;
import dao.UuidgeneratorDao;
import model.Company;
import model.Password;
import model.User;

@Controller
public class HomeController extends AbstractController {

  public HomeController() throws IllegalArgumentException, IllegalAccessException {
    super();
  }

  @Autowired
  @Qualifier("UserDao")
  private UserDao userDao;

  @Autowired
  @Qualifier("UuidgeneratorDao")
  private UuidgeneratorDao registrationDao;

  @Autowired
  @Qualifier("CompanyDao")
  private CompanyDao companyDao;

  @Autowired
  @Qualifier("StateDao")
  private StateDao stateDao;

  @RequestMapping(value = {"/", "/index.html"})
  public String index(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    var user = (User) session.getAttribute(SessionName.USER);
    modelmap.addAttribute("profileImage", Util.convertToBase64FromByte(user.getImg()));
    modelmap.addAttribute("name", user.getName());
    return "Home/index";
  }

  @RequestMapping(value = "/logout.html")
  public String logout(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    session.invalidate();
    return super.redirect("login.html");
  }

  @RequestMapping(value = "/login.html", method = RequestMethod.GET)
  public String login(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    var user = (User) session.getAttribute(SessionName.USER);
    if (user != null) {
      return super.redirectMainPage();
    }
    return "Home/login";
  }


  @RequestMapping(value = "/login.html", method = RequestMethod.POST)
  public String signin(@ModelAttribute JoinBean join, ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    if (!validateSignin(join)) {
      return "Home/login";
    }
    var user = userDao.signIn(join.getUsername(), Util.convertMD5(join.getPassword()));
    if (user == null) {
      modelmap.addAttribute("failedLogin", "Wrong password. Try again or click Forgot password to reset it.");
      return "Home/login";
    }
    session.setAttribute(SessionName.USER, user);
    return super.redirectMainPage();
  }

  @RequestMapping(value = "/register.html")
  public String register(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Home/register";
  }

  @RequestMapping(value = "/register-email.html", method = RequestMethod.POST)
  public String registerEmail(@ModelAttribute JoinBean join, ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    if (Util.isNullAndWhiteSpace(join.getEmailAddress()) || join.getEmailAddress().indexOf('@') < 0) {
      return "Home/register";
    }
    new Thread(() -> {
      var user = userDao.findById(join.getEmailAddress());
      try {
        var mail = new MailManager();
        mail.setAddress(PropertyMap.getInstance().getProperty("config", "mail_id"), join.getEmailAddress(), null, null);
        if (user == null) {
          super.getLogger().info("This user is nothing. " + join.getEmailAddress());
          var uuid = registrationDao.getUUIDRegistraion(join.getEmailAddress());
          mail.setSubject("[WorkBoard]Verification mail");
          super.getLogger().debug("template url - " + LocalPaths.getMailTempaltePath() + File.separator + "registration.html");
          try (var stream = new FileInputStream(LocalPaths.getMailTempaltePath() + File.separator + "registration.html")) {
            byte[] data = stream.readAllBytes();
            var contents = new String(data, "UTF-8");
            var url = PropertyMap.getInstance().getProperty("config", "host_address") + "join.html?email=" + uuid.getEmail() + "&key=" + uuid.getUuid();
            contents = contents.replace("#####HOME#####", PropertyMap.getInstance().getProperty("config", "host_address") + "login.html");
            contents = contents.replace("#####URL#####", url);
            super.getLogger().debug("#####HOME#####     " + PropertyMap.getInstance().getProperty("config", "host_address") + "login.html");
            super.getLogger().debug("#####URL#####     " + url);
            mail.setContent(contents);
          }
        } else {
          // TODO: go to password
          super.getLogger().info("The user already exists. " + join.getEmailAddress());
        }
        mail.sendMail();
        super.getLogger().info("The mail send " + join.getEmailAddress());
      } catch (Throwable e) {
        super.getLogger().error(join.getEmailAddress() + "\r\n" + e);
        e.printStackTrace();
      }
    }).start();
    return "Home/register-email";
  }

  @RequestMapping(value = "/join.html", method = RequestMethod.GET)
  public String joinAdmin(@ModelAttribute JoinBean join, ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    if (!validateJoinEntity1(join, false)) {
      return "Home/register";
    }
    super.getLogger().info("connection path. email = " + join.getEmail() + " key = " + join.getKey());
    modelmap.addAttribute("email", join.getEmail());
    modelmap.addAttribute("key", join.getKey());
    modelmap.addAttribute("type", "admin");
    return "Home/join";
  }

  @RequestMapping(value = "/join.html", method = RequestMethod.POST)
  public String signup(@ModelAttribute JoinBean join, ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    if (!validateJoinEntity1(join, true)) {
      return redirect("login.html");
    }
    if (!validateJoinEntity2(join)) {
      modelmap.addAttribute("email", join.getEmail());
      modelmap.addAttribute("key", join.getKey());
      modelmap.addAttribute("type", join.getType());
      return "Home/join";
    }
    if (!validationPasswd(join.getPassword())) {
      super.getLogger().info("Invalid connection path. The password is not match to regex.");
      return "Home/join";
    }

    var company = createCompany(join);
    companyDao.create(company);
    var user = createUser(join, company);
    userDao.create(user);

    registrationDao.changeStateUUIDRegistraion(join.getEmail());

    return redirect("login.html");
  }


  private Company createCompany(JoinBean join) {
    var company = new Company();
    company.setName(join.getCompanyName());
    company.setState(stateDao.Active());
    company.setCreateDate(new Date());
    return company;
  }

  private User createUser(JoinBean join, Company company) {
    var user = new User();
    user.setId(join.getEmail());
    var name = join.getEmail();
    name = name.substring(0, name.indexOf("@"));
    user.setName(name);
    var profilename = name;
    if (profilename.length() > 1) {
      profilename = profilename.substring(0, 2);
    }
    user.setImg(Util.createProfileImage(profilename.toUpperCase()));
    user.setState(stateDao.Active());
    user.setCreateDate(new Date());
    user.setAdmin(true);
    user.setCompanyBean(company);
    var pwd = new Password();
    pwd.setPassword(Util.convertMD5(join.getPassword()));
    pwd.setState(stateDao.Active());
    pwd.setCreateDate(new Date());
    user.setPasswords(new ArrayList<>());
    user.addPassword(pwd);

    return user;
  }

  private boolean validationPasswd(String pw) {
    var p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
    var m = p.matcher(pw);
    if (m.matches()) {
      return true;
    }
    return false;
  }

  private boolean validateJoinEntity1(JoinBean join, boolean checkType) {
    if (Util.isNullAndWhiteSpace(join.getEmail())) {
      super.getLogger().info("Invalid connection path. The email is null.");
      return false;
    }
    if (Util.isNullAndWhiteSpace(join.getKey())) {
      super.getLogger().info("Invalid connection path. The key is null.");
      return false;
    }
    if (!registrationDao.isHave(join.getEmail(), join.getKey())) {
      super.getLogger().info("Invalid connection path. This key was not applied.");
      return false;
    }
    if (checkType) {
      if (Util.isNullAndWhiteSpace(join.getType())) {
        super.getLogger().info("Invalid connection path. The type is null.");
        return false;
      }
    }
    return true;
  }

  private boolean validateJoinEntity2(JoinBean join) {
    if (Util.isNullAndWhiteSpace(join.getCompanyName())) {
      super.getLogger().info("Invalid connection path. The company name is null.");
      return false;
    }
    if (Util.isNullAndWhiteSpace(join.getPassword())) {
      super.getLogger().info("Invalid connection path. The password is null.");
      return false;
    }
    if (Util.isNullAndWhiteSpace(join.getTerms())) {
      super.getLogger().info("Invalid connection path. The terms is null.");
      return false;
    }
    return true;
  }

  private boolean validateSignin(JoinBean join) {
    if (Util.isNullAndWhiteSpace(join.getUsername())) {
      super.getLogger().info("Invalid connection path. The email is null.");
      return false;
    }
    if (Util.isNullAndWhiteSpace(join.getPassword())) {
      super.getLogger().info("Invalid connection path. The password is null.");
      return false;
    }
    return true;
  }
}
