package common;

import org.apache.log4j.Logger;

public class AbstractController {

  private Logger logger = null;

  protected Logger getLogger() {
    if (logger == null) {
      logger = LoggerManager.getLogger(this.getClass());
    }
    return logger;
  }
}
