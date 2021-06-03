package controller;

import java.io.File;
import java.io.FileInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import bean.JoinBean;
import common.AbstractController;
import common.AllocDao;
import common.FactoryDao;
import common.LocalPaths;
import common.MailManager;
import common.PropertyMap;
import common.Util;
import dao.UserDao;
import dao.UuidgeneratorDao;
import model.User;
import model.Uuidgenerator;

@Controller
public class HomeController extends AbstractController {

  public HomeController() throws IllegalArgumentException, IllegalAccessException {
    super();
  }

  @AllocDao
  private UserDao userDao;

  @AllocDao
  private UuidgeneratorDao registrationDao;

  @RequestMapping(value = "/index.html")
  public String index(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Home/index";
  }

  @RequestMapping(value = "/login.html")
  public String login(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Home/login";
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
      User user = userDao.findById(join.getEmailAddress());
      try {
        MailManager mail = new MailManager();
        mail.setAddress(PropertyMap.getInstance().getProperty("config", "mail_id"), join.getEmailAddress(), null, null);
        if (user == null) {
          super.getLogger().info("This user is nothing. " + join.getEmailAddress());
          Uuidgenerator uuid = registrationDao.getUUID(join.getEmailAddress());
          mail.setSubject("[WorkBoard]Verification mail");
          super.getLogger().debug("template url - " + LocalPaths.getMailTempaltePath() + File.separator + "registration.html");
          try (FileInputStream stream = new FileInputStream(LocalPaths.getMailTempaltePath() + File.separator + "registration.html")) {
            byte[] data = stream.readAllBytes();
            String contents = new String(data, "UTF-8");
            String url = PropertyMap.getInstance().getProperty("config", "host_address") + "join.html?email=" + uuid.getEmail() + "&key=" + uuid.getUuid();
            contents = contents.replace("#####HOME#####", PropertyMap.getInstance().getProperty("config", "host_address") + "login.html");
            contents = contents.replace("#####URL#####", url);
            super.getLogger().debug("#####HOME#####     " + PropertyMap.getInstance().getProperty("config", "host_address") + "login.html");
            super.getLogger().debug("#####URL#####     " + url);
            mail.setContent(contents);
          }
        } else {
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
    if (Util.isNullAndWhiteSpace(join.getEmail())) {
      super.getLogger().info("Invalid connection path. The email is null.");
      return "Home/register";
    }
    if (Util.isNullAndWhiteSpace(join.getKey())) {
      super.getLogger().info("Invalid connection path. The key is null.");
      return "Home/register";
    }
    if (!FactoryDao.getDao(UuidgeneratorDao.class).isHave(join.getEmail(), join.getKey())) {
      super.getLogger().info("Invalid connection path. This key was not applied.");
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
    if (Util.isNullAndWhiteSpace(join.getEmail())) {
      super.getLogger().info("Invalid connection path. The email is null.");
      return redirect("login.html");
    }
    if (Util.isNullAndWhiteSpace(join.getKey())) {
      super.getLogger().info("Invalid connection path. The email is null.");
      return redirect("login.html");
    }
    if (Util.isNullAndWhiteSpace(join.getType())) {
      super.getLogger().info("Invalid connection path. The type is null.");
      return redirect("login.html");
    }
    if (!FactoryDao.getDao(UuidgeneratorDao.class).isHave(join.getEmail(), join.getKey())) {
      super.getLogger().info("Invalid connection path. This key was not applied.");
      return redirect("login.html");
    }
    if (Util.isNullAndWhiteSpace(join.getCompanyName())) {
      super.getLogger().info("Invalid connection path. The company name is null.");
      modelmap.addAttribute("email", join.getEmail());
      modelmap.addAttribute("key", join.getKey());
      modelmap.addAttribute("type", join.getType());
      return "Home/join";
    }
    if (Util.isNullAndWhiteSpace(join.getPassword())) {
      super.getLogger().info("Invalid connection path. The password is null.");
      modelmap.addAttribute("email", join.getEmail());
      modelmap.addAttribute("key", join.getKey());
      modelmap.addAttribute("type", join.getType());
      return "Home/join";
    }
    if (Util.isNullAndWhiteSpace(join.getTerms())) {
      super.getLogger().info("Invalid connection path. The terms is null.");
      modelmap.addAttribute("email", join.getEmail());
      modelmap.addAttribute("key", join.getKey());
      modelmap.addAttribute("type", join.getType());
      return "Home/join";
    }
    // Sign up
    return redirect("login.html");
  }
}
