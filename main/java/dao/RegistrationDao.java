package dao;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import common.AbstractDao;
import common.FactoryDao;
import model.Uuidgenerator;

public class RegistrationDao extends AbstractDao<Uuidgenerator> {

  protected RegistrationDao() {
    super(Uuidgenerator.class);
  }

  @SuppressWarnings("unchecked")
  public List<Uuidgenerator> findByEmail(String email) {
    return transaction((em) -> {
      try {
        Query query = em.createNamedQuery("Registration.findByEmail", Uuidgenerator.class);
        query.setParameter("email", email);
        query.setParameter("state", FactoryDao.getDao(StateDao.class).DELETE);
        return (List<Uuidgenerator>) query.getResultList();
      } catch (NoResultException e) {
        return null;
      }
    });
  }
}
