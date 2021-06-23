package common;

import org.apache.log4j.Logger;

public class AbstractController {

  private Logger logger = null;

  public AbstractController() {
  }

  protected Logger getLogger() {
    if (logger == null) {
      logger = LoggerManager.getLogger(this.getClass());
    }
    return logger;
  }

  protected String redirect(String url) {
    return "redirect:" + url;
  }
}
