package common;

import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Logger;
import model.User;

public class AbstractController {

  private Logger logger = null;

  public AbstractController() {}

  protected Logger getLogger() {
    if (logger == null) {
      logger = LoggerManager.getLogger(this.getClass());
    }
    return logger;
  }

  protected String redirect(String url) {
    return "redirect:" + url;
  }

  protected String redirectMainPage() {
    return redirect("/");
  }

  protected User getCurrentUser(HttpSession session) {
    return (User) session.getAttribute(SessionName.USER);
  }
}
