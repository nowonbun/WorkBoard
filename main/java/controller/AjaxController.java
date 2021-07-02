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
import common.Util;
import dao.MenuDao;
import model.Menu;

@Controller
public class AjaxController {

  @Autowired
  @Qualifier("MenuDao")
  private MenuDao menuDao;

  @RequestMapping(value = "menu.json", method = RequestMethod.GET)
  @ResponseBody
  public String index(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    List<MenuBean> menus = new ArrayList<>();
    for (Menu menu : menuDao.getList()) {
      // to work
      if (menu.getParent() != null) {
        continue;
      }
      MenuBean bean = new MenuBean();
      menus.add(bean);
      bean.setTitle(menu.getName());
      bean.setUrl(menu.getUrl());
      if (menu.getChilds().size() > 0) {
        bean.setList(new ArrayList<>());
      }
      for (Menu sub : menu.getChilds()) {
        MenuBean subbean = new MenuBean();
        bean.getList().add(subbean);
        subbean.setTitle(sub.getName());
        subbean.setUrl(sub.getUrl());
      }
    }
    ;
    return Util.convertToJsonFromObject(menus);
  }
}
