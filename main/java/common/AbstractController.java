package common;

import java.lang.reflect.Field;
import org.apache.log4j.Logger;

public class AbstractController {

  private Logger logger = null;

  public AbstractController() {
    for (Field field : this.getClass().getDeclaredFields()) {
      AllocDao ad = field.getDeclaredAnnotation(AllocDao.class);
      if (ad != null) {
        field.setAccessible(true);
        try {
          field.set(this, FactoryDao.getDao(field.getType()));
        } catch (IllegalArgumentException | IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }

  protected Logger getLogger() {
    if (logger == null) {
      logger = LoggerManager.getLogger(this.getClass());
    }
    return logger;
  }
}
