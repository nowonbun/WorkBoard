package bean;

import java.util.List;

public class MenuBean {
  private String title;
  private String url;
  private List<MenuBean> list;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<MenuBean> getList() {
    return list;
  }

  public void setList(List<MenuBean> list) {
    this.list = list;
  }

}
