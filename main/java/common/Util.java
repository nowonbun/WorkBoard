package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Util {
  private final static DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");

  public static String createUUIDKey() {
    String key = UUID.randomUUID().toString();
    return key.replace("-", "") + dateFormat2.format(new Date());
  }
  public static boolean isNullAndWhiteSpace(String val) {
    if(val == null) {
      return true;
    }
    if("".equalsIgnoreCase(val.trim())) {
      return true;
    }
    return false;
  }
}
