package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import bean.MenuBean;
import common.AbstractController;
import common.Util;
import dao.MenuDao;
import model.Menu;

@Controller
public class MenuController extends AbstractController {


  @Autowired
  @Qualifier("MenuDao")
  private MenuDao menuDao;

  private MenuBean createMenu(Menu menu) {
    MenuBean bean = new MenuBean();
    bean.setTitle(menu.getName());
    bean.setUrl(menu.getUrl());
    bean.setIcon(menu.getIcon());
    bean.setCode(menu.getCode());
    if (menu.getChilds().size() > 0) {
      bean.setList(new ArrayList<>());
    }
    for (Menu sub : menu.getChilds()) {
      bean.getList().add(createMenu(sub));
    }
    return bean;
  }

  @RequestMapping(value = "menu.json", method = RequestMethod.POST)
  @ResponseBody
  public String menu(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    List<MenuBean> menus = new ArrayList<>();
    for (Menu menu : menuDao.getList()) {
      // to work
      if (menu.getParent() != null) {
        continue;
      }
      if (!super.getCurrentUser(session).isAdmin()) {
        if (menu.getIsadmin()) {
          continue;
        }
      }
      menus.add(createMenu(menu));
    }
    return Util.convertToJsonFromObject(menus);
  }
}
