package common;

import java.io.File;

public class LocalPaths {
  private String webContentPath = null;

  private static LocalPaths instance = null;

  private LocalPaths() {

  }

  public static LocalPaths getInstance() {
    if (instance == null) {
      instance = new LocalPaths();
    }
    return instance;
  }

  public void setWebRootPath(String path) {
    this.webContentPath = path;
  }

  public static String getWebRootPath() {
    return getInstance().webContentPath;
  }

  public static String getClassPath() {
    return getWebRootPath() + File.separator + "WEB-INF" + File.separator + "classes";
  }

  public static String getViewPath() {
    return getWebRootPath() + File.separator + "WEB-INF" + File.separator + "view";
  }

  public static String getMailTempaltePath() {
    return getWebRootPath() + File.separator + "WEB-INF" + File.separator + "MailTemplate";
  }

}
