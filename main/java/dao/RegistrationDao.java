package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import common.AbstractDao;
import common.FactoryDao;
import model.Registration;

public class RegistrationDao extends AbstractDao<Registration> {

  protected RegistrationDao() {
    super(Registration.class);
  }

  @SuppressWarnings("unchecked")
  public List<Registration> findByEmail(String email) {
    return transaction((em) -> {
      try {
        Query query = em.createNamedQuery("Registration.findByEmail", Registration.class);
        query.setParameter("email", email);
        query.setParameter("state", FactoryDao.getDao(StateDao.class).DELETE);
        return (List<Registration>) query.getResultList();
      } catch (NoResultException e) {
        return null;
      }
    });
  }
}
