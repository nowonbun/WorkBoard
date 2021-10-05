package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import common.AbstractDao;
import model.State;

public class StateDao extends AbstractDao<State> {

  private Map<String, State> map = null;
  private final String ACTIVE = "ACTI";
  private final String DELETE = "DELE";
  private final String USED = "USED";

  protected StateDao() {
    super(State.class);
    if (map == null) {
      clear();
    }
  }

  public void clear() {
    this.map = transaction((em) -> {
      try {
        var query = em.createNamedQuery("State.findActiveAll", State.class);
        var ret = new HashMap<String, State>();
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
