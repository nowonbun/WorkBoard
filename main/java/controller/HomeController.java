package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
  @RequestMapping(value = "/index.html")
  public String index(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "index";
  }

  @RequestMapping(value = "/login.html")
  public String login(ModelMap modelmap, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "login";
  }
}
