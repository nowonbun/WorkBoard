package controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import bean.JoinBean;
import bean.ProfileBean;
import common.AbstractController;
import common.SessionName;
import common.Util;
import dao.StateDao;
import dao.UserDao;
import model.Password;
import model.User;

@Controller
@RequestMapping(value = "/setting")
public class SettingController extends AbstractController {

  @Autowired
  @Qualifier("UserDao")
  private UserDao userDao;

  @Autowired
  @Qualifier("StateDao")
  private StateDao stateDao;

  @RequestMapping(value = "user.html")
  public String user(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/user";
  }

  @RequestMapping(value = "project.html")
  public String project(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/project";
  }

  @RequestMapping(value = "wizard.html")
  public String wizard(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/wizard";
  }

  @RequestMapping(value = "profile.html")
  public String profile(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/profile";
  }

  @RequestMapping(value = "profile.json", method = RequestMethod.POST)
  @ResponseBody
  public String profileData(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    User user = super.getCurrentUser(session);
    ProfileBean profile = new ProfileBean();
    profile.setCompany(user.getCompany().getName());
    profile.setGroup("");
    profile.setUid(user.getId());
    profile.setName(user.getName());
    profile.setImage(Util.convertToBase64FromByte(user.getImg()));
    profile.setAdmin(user.isAdmin());
    return Util.convertToJsonFromObject(profile);
  }

  @RequestMapping(value = "profileModify.json", method = RequestMethod.POST)
  @ResponseBody
  public String profileModify(@ModelAttribute JoinBean join, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    User user = super.getCurrentUser(session);
    //user = userDao.findById(user.getId());
    if (Util.isNullAndWhiteSpace(join.getUsername())) {
      return JsonResponse(false, "The data is fault.");
    }
    user.setName(join.getUsername());
    if (!Util.isNullAndWhiteSpace(join.getPassword())) {
      for (Password password : user.getPasswords()) {
        password.setState(stateDao.Delete());
      }
      Password pwd = new Password();
      pwd.setPassword(Util.convertMD5(join.getPassword()));
      pwd.setState(stateDao.Active());
      pwd.setCreateDate(new Date());
      user.addPassword(pwd);
    }
    if (!Util.isNullAndWhiteSpace(join.getImage())) {
      user.setImg(Util.convertToByteFromBase64(join.getImage()));
    }
    if (join.getIsAdmin() != user.isAdmin()) {
      if (!join.getIsAdmin()) {
        long count = userDao.CountAdmin(user.getCompany());
        if (count < 2) {
          return JsonResponse(false, "The administrator of this company is disappears");
        }
      }
      user.setAdmin(join.getIsAdmin());
    }
    userDao.update(user);
    session.setAttribute(SessionName.USER, user);
    return JsonResponse(true, "The profile is updated.");
  }


  @RequestMapping(value = "permission.html")
  public String permission(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/permission";
  }
}
