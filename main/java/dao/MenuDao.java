package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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

  @SuppressWarnings("unchecked")
  public void clear() {
    this.map = transaction((em) -> {
      try {
        Query query = em.createNamedQuery("Menu.findActiveAll", Menu.class);
        Map<String, Menu> ret = new HashMap<>();
        list = (List<Menu>) query.getResultList();
        list.forEach(x -> {
          ret.put(x.getCode(), x);
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
