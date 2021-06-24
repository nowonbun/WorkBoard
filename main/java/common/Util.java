package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    if (val == null) {
      return true;
    }
    if ("".equalsIgnoreCase(val.trim())) {
      return true;
    }
    return false;
  }

  public static String convertMD5(String val) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(val.getBytes());
      byte byteData[] = md.digest();
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < byteData.length; i++) {
          sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString();

    } catch (NoSuchAlgorithmException e) {
      LoggerManager.getLogger(Util.class).error(e);
      return null;
    }
  }
}
