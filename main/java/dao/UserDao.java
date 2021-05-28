package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import common.AbstractDao;
import model.User;

public class UserDao extends AbstractDao<User> {

  protected UserDao() {
    super(User.class);
  }

  @SuppressWarnings("unchecked")
  public List<User> findAll() {
    return transaction((em) -> {
      try {
        Query query = em.createNamedQuery("User.findAll", User.class);
        return (List<User>) query.getResultList();
      } catch (NoResultException e) {
        return null;
      }
    });
  }
}
