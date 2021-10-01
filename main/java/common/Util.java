package common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import javax.imageio.ImageIO;
import com.google.gson.Gson;

public class Util {
  private final static DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
  private final static Gson gson = new Gson();

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

  public static byte[] createProfileImage(String name) {
    BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      Graphics2D graphics = img.createGraphics();
      graphics.setColor(Color.WHITE);
      graphics.fillRect(0, 0, 100, 100);
      graphics.setColor(Color.BLACK);
      graphics.setFont(new Font("Arial", Font.BOLD, 50));
      graphics.drawString(name, 15, 70);
      ImageIO.write(img, "png", baos);
      return baos.toByteArray();
    } catch (IOException e) {
      LoggerManager.getLogger(Util.class).error(e);
      return null;
    }
  }

  public static String convertToBase64FromByte(byte[] data) {
    return Base64.getEncoder().encodeToString(data);
  }

  public static byte[] convertToByteFromBase64(String data) {
    return Base64.getDecoder().decode(data);
  }

  public static <T> T convertToObjectFromJson(String json, Class<T> clz) {
    return gson.fromJson(json, clz);
  }

  public static String convertToJsonFromObject(Object obj) {
    return gson.toJson(obj);
  }
  
  public static String convertOX(boolean val) {
    if(val) {
      return "〇";
    } else {
      return "Ｘ";
    }
  }
}
