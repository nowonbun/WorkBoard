package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import common.AbstractController;
import common.AllocDao;
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
    // This
    User user = userDao.findById(emailAddress);
    if (user == null) {
      Uuidgenerator uuid = registrationDao.getUUID(emailAddress);
      System.out.println(uuid);
    }
    return "Home/register-email";
  }
}
