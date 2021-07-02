package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@NamedQueries({
  @NamedQuery(name = "Menu.findAll", query="SELECT m FROM Menu m"),
  @NamedQuery(name = "Menu.findActiveAll", query = "SELECT m FROM Menu m WHERE m.isactive=true")
})
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;
	
	private boolean isadmin;

	private boolean isactive;
	
	private String name;

	private String url;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subMenu")
	private Menu menu;

	@OneToMany(mappedBy="menu", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Menu> menus;

	public Menu() {
	}

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

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Menu addMenus(Menu menus) {
		getMenus().add(menus);
		menus.setMenu(this);

		return menus;
	}

	public Menu removeMenus(Menu menus) {
		getMenus().remove(menus);
		menus.setMenu(null);

		return menus;
	}

}