package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import common.AbstractDao;
import model.Type;

public class TypeDao extends AbstractDao<Type> {

  private Map<String, Type> map = null;
  public final String REGISTRATION = "REGI";
  public final String PASSWORD = "PSSW";

  protected TypeDao() {
    super(Type.class);
    if (map == null) {
      clear();
    }
  }

  @SuppressWarnings("unchecked")
  public void clear() {
    this.map = transaction((em) -> {
      try {
        Query query = em.createNamedQuery("Type.findActiveAll", Type.class);
        map = new HashMap<>();
        ((List<Type>) query.getResultList()).forEach(x -> {
          map.put(x.getCode(), x);
        });
        return map;
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public Type Registraion() {
    return this.map.get(REGISTRATION);
  }

  public Type Password() {
    return this.map.get(PASSWORD);
  }
}
