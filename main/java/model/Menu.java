package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@NamedQueries({@NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"), 
  @NamedQuery(name = "Menu.findActiveAll", query = "SELECT m FROM Menu m WHERE m.isactive=true order by m.displayOrder asc")})
public class Menu implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String code;

  private boolean isadmin;

  private boolean isactive;

  private String name;

  private String url;
  
  private String icon;
  
  private Integer displayOrder;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "parent")
  private Menu parent;

  @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Menu> childs;
  
  public Menu() {}

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean getIsactive() {
    return this.isactive;
  }

  public void setIsactive(boolean isactive) {
    this.isactive = isactive;
  }

  public boolean getIsadmin() {
    return this.isadmin;
  }

  public void setIsadmin(boolean isadmin) {
    this.isadmin = isadmin;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getIcon() {
    return this.icon;
  }
  
  public void setIcon(String icon) {
    this.icon = icon;
  }
  
  public Integer getDisplayOrder() {
    return this.displayOrder;
  }
  
  public void setDisplayOrder(Integer displayOrder) {
    this.displayOrder = displayOrder;
  }

  public Menu getParent() {
    return this.parent;
  }

  public void setParent(Menu parent) {
    this.parent = parent;
  }

  public List<Menu> getChilds() {
    return this.childs;
  }

  public void setChilds(List<Menu> childs) {
    this.childs = childs;
  }

  public Menu addPerents(Menu menus) {
    getChilds().add(menus);
    menus.setParent(this);

    return menus;
  }

  public Menu removeParents(Menu menus) {
    getChilds().remove(menus);
    menus.setParent(null);

    return menus;
  }

}
