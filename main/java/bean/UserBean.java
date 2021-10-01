package bean;

public class UserBean {
  private String id;
  private String name;
  private String state;
  private String isAdmin;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String isState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String isAdmin() {
    return isAdmin;
  }

  public void setAdmin(String isAdmin) {
    this.isAdmin = isAdmin;
  }

}
