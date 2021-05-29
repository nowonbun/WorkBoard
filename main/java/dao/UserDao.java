package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import common.AbstractDao;
import common.FactoryDao;
import model.User;

@SuppressWarnings("unchecked")
public class UserDao extends AbstractDao<User> {

  protected UserDao() {
    super(User.class);
  }

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

  public User findById(String id) {
    return transaction((em) -> {
      try {
        Query query = em.createNamedQuery("User.findById", User.class);
        query.setParameter("id", id);
        query.setParameter("state", FactoryDao.getDao(StateDao.class).Active());
        return (User) query.getSingleResult();
      } catch (NoResultException e) {
        return null;
      }
    });
  }
}
