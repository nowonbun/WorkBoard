package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import common.AbstractDao;
import model.Type;

public class TypeDao extends AbstractDao<Type> {

  private List<Type> list = null;
  public final String REGISTRATION = "REGI";
  public final String PASSWORD = "PSSW";

  protected TypeDao() {
    super(Type.class);
  }

  @SuppressWarnings("unchecked")
  public void clear() {
    this.list = transaction((em) -> {
      try {
        Query query = em.createNamedQuery("Type.findActiveAll", Type.class);
        return (List<Type>) query.getResultList();
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public Type Registraion() {
    return this.list.stream().filter(x -> REGISTRATION.equals(x.getCode())).findFirst().get();
  }

  public Type Password() {
    return this.list.stream().filter(x -> PASSWORD.equals(x.getCode())).findFirst().get();
  }
}
