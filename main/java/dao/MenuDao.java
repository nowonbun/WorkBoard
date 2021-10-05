package dao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
import common.AbstractDao;
import model.Menu;

public class MenuDao extends AbstractDao<Menu> {

  private List<Menu> list = null;
  private Map<String, Menu> map = null;

  protected MenuDao() {
    super(Menu.class);
    if (map == null) {
      clear();
    }
  }

  public void clear() {
    this.map = transaction((em) -> {
      try {
        var query = em.createNamedQuery("Menu.findActiveAll", Menu.class);
        var ret = new HashMap<String, Menu>();
        list = (List<Menu>) query.getResultList();
        list.forEach(x -> {
          ret.put(x.getCode(), x);
          if (x.getChilds() != null && x.getChilds().size() > 0) {
            x.setChilds(x.getChilds().stream().sorted(Comparator.comparing(Menu::getDisplayOrder)).collect(Collectors.toList()));
          }
        });
        return ret;
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public List<Menu> getList() {
    return list.stream().collect(Collectors.toList());
  }
}
