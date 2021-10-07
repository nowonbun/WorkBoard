package controller;

import java.util.ArrayList;
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
import bean.GroupBean;
import bean.JoinBean;
import bean.MessageBean;
import bean.ProfileBean;
import bean.UserBean;
import common.AbstractController;
import common.SessionName;
import common.Util;
import dao.GroupteamDao;
import dao.StateDao;
import dao.UserDao;
import model.Groupteam;
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

  @Autowired
  @Qualifier("GroupteamDao")
  private GroupteamDao groupteamDao;

  @RequestMapping(value = "user.html")
  public String dispUser(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/user";
  }

  @RequestMapping(value = "project.html")
  public String dispProject(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/project";
  }

  @RequestMapping(value = "wizard.html")
  public String dispWizard(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/wizard";
  }

  @RequestMapping(value = "profile.html")
  public String dispProfile(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/profile";
  }

  @RequestMapping(value = "permission.html")
  public String dispPermission(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/permission";
  }

  @RequestMapping(value = "group.html")
  public String dispGroup(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Setting/group";
  }

  @RequestMapping(value = "profile.json", method = RequestMethod.POST)
  @ResponseBody
  public String getProfileData(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
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
  public String getProfileModify(@ModelAttribute JoinBean join, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    User user = super.getCurrentUser(session);
    user = userDao.findById(user.getId());
    if (Util.isNullAndWhiteSpace(join.getUsername())) {
      return MessageBean.Message(false, "The data is fault.");
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
        long count = userDao.countAdmin(user.getCompany());
        if (count < 2) {
          return MessageBean.Message(false, "The administrator of this company is disappears");
        }
      }
      user.setAdmin(join.getIsAdmin());
    }
    userDao.update(user);
    session.setAttribute(SessionName.USER, user);
    return MessageBean.Message(true, "The profile is updated.");
  }

  @RequestMapping(value = "userlist.json", method = RequestMethod.POST)
  @ResponseBody
  public String getUserList(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    var user = super.getCurrentUser(session);
    var beanList = new ArrayList<UserBean>();
    for (var u : userDao.getUserList(user.getCompany())) {
      var node = new UserBean();
      node.setId(u.getId());
      node.setName(u.getName());
      node.setState(Util.convertOX(stateDao.Active(), u.getState()));
      node.setAdmin(Util.convertOX(u.isAdmin()));
      beanList.add(node);
    }
    return Util.convertToJsonFromObject(beanList);
  }

  @RequestMapping(value = "grouplist.json", method = RequestMethod.POST)
  @ResponseBody
  public String getGroupList(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    var user = super.getCurrentUser(session);
    var beanList = new ArrayList<GroupBean>();
    for (var g : groupteamDao.findAllByCompany(user.getCompany())) {
      var node = new GroupBean();
      node.setIdx(g.getIdx());
      node.setName(g.getName());
      node.setState(Util.convertOX(stateDao.Active(), g.getState()));
      node.setActive(stateDao.Active().getCode().equals(g.getState().getCode()));
      beanList.add(node);
    }
    return Util.convertToJsonFromObject(beanList);
  }

  @RequestMapping(value = "addGroupName.json", method = RequestMethod.POST)
  @ResponseBody
  public String addGroupName(@ModelAttribute GroupBean group, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    var user = super.getCurrentUser(session);
    if (groupteamDao.findByName(user.getCompany(), group.getName()) != null) {
      return MessageBean.Message(false, "The group name is exist.");
    }
    var groupteam = new Groupteam();
    groupteam.setName(group.getName());
    groupteam.setCompany(user.getCompany());
    groupteam.setState(stateDao.Active());
    groupteam.setCreateDate(new Date());
    groupteamDao.create(groupteam);
    return MessageBean.Message(true, "The group name is added.");
  }

  @RequestMapping(value = "modifyGroupName.json", method = RequestMethod.POST)
  @ResponseBody
  public String modifyGroupName(@ModelAttribute GroupBean group, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    var user = super.getCurrentUser(session);
    var groupteam = groupteamDao.findById(user.getCompany(), group.getIdx());
    if (groupteam == null) {
      return MessageBean.Message(false, "The group name is null.");
    }
    if (groupteamDao.findByNameWithoutId(user.getCompany(), group.getIdx(), group.getName()) != null) {
      return MessageBean.Message(false, "The group name is exist.");
    }
    groupteam.setName(group.getName());
    groupteam.setCompany(user.getCompany());
    groupteam.setState(group.isActive() ? stateDao.Active() : stateDao.Delete());
    groupteam.setLastUpdate(new Date());
    groupteamDao.update(groupteam);
    return MessageBean.Message(true, "The group name is updated.");
  }
}
