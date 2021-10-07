package bean;

import common.Util;

public class MessageBean {
  private boolean success;
  private String message;

  public MessageBean(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String ToJson() {
    return Util.convertToJsonFromObject(this);
  }

  public static String Message(boolean success, String message) {
    return new MessageBean(success, message).ToJson();
  }
}
