package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import common.AbstractDao;
import model.State;

public class StateDao extends AbstractDao<State> {

  private List<State> list = null;
  public final String ACTIVE = "ACTI";
  public final String DELETE = "DELE";
  public final String USED = "USED";

  protected StateDao() {
    super(State.class);
  }

  @SuppressWarnings("unchecked")
  public void clear() {
    this.list = transaction((em) -> {
      try {
        Query query = em.createNamedQuery("State.findActiveAll", State.class);
        return (List<State>) query.getResultList();
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public State Active() {
    return this.list.stream().filter(x -> ACTIVE.equals(x.getCode())).findFirst().get();
  }

  public State Delete() {
    return this.list.stream().filter(x -> DELETE.equals(x.getCode())).findFirst().get();
  }

  public State Used() {
    return this.list.stream().filter(x -> USED.equals(x.getCode())).findFirst().get();
  }
}
