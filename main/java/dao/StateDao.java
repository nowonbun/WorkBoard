package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import common.AbstractDao;
import model.State;

public class StateDao extends AbstractDao<State> {

  private Map<String, State> map = null;
  public final String ACTIVE = "ACTI";
  public final String DELETE = "DELE";
  public final String USED = "USED";

  protected StateDao() {
    super(State.class);
    if(map == null) {
      clear();
    }
  }

  @SuppressWarnings("unchecked")
  public void clear() {
    this.map = transaction((em) -> {
      try {
        Query query = em.createNamedQuery("State.findActiveAll", State.class);
        Map<String, State> ret = new HashMap<>();
        ((List<State>) query.getResultList()).forEach(x -> {
          ret.put(x.getCode(), x);
        });
        return ret;
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public State Active() {
    return map.get(ACTIVE);
  }

  public State Delete() {
    return map.get(DELETE);
  }

  public State Used() {
    return map.get(USED);
  }
}
