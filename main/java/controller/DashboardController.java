package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import common.AbstractController;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends AbstractController {
  @RequestMapping(value = "index.html")
  public String index(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
    return "Dashboard/index";
  }
}
