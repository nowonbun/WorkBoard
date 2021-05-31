package controller;

import java.io.File;
import java.io.FileInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import common.AbstractController;
import common.AllocDao;
import common.LocalPaths;
import common.MailManager;
import common.PropertyMap;
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

  @RequestMapping(value = "/register-email.html")
  public String registerEmail(@RequestParam("emailAddress") String emailAddress, ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    new Thread(() -> {
      User user = userDao.findById(emailAddress);
      try {
        MailManager mail = new MailManager();
        mail.setAddress(PropertyMap.getInstance().getProperty("config", "mail_id"), emailAddress, null, null);
        if (user == null) {
          Uuidgenerator uuid = registrationDao.getUUID(emailAddress);
          mail.setSubject("[WorkBoard]Verification mail");
          try (FileInputStream stream = new FileInputStream(LocalPaths.getMailTempaltePath() + File.separator + "registration.html")) {
            byte[] data = stream.readAllBytes();
            String contents = new String(data, "UTF-8");
            String url = PropertyMap.getInstance().getProperty("config", "host_address") + "join.html?email=" + uuid.getEmail() + "&key = " + uuid.getUuid();
            contents.replaceAll("#####HOME#####", PropertyMap.getInstance().getProperty("config", "host_address") + "login.html");
            contents.replaceAll("#####URL#####", url);
            mail.setContent(contents);
          }
        } else {

        }
        mail.sendMail();
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }).start();
    return "Home/register-email";
  }
}
