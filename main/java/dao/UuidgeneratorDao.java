package dao;

import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import common.AbstractDao;
import common.FactoryDao;
import common.Util;
import model.Uuidgenerator;

//@SuppressWarnings("unchecked")
public class UuidgeneratorDao extends AbstractDao<Uuidgenerator> {

  protected UuidgeneratorDao() {
    super(Uuidgenerator.class);
  }


  public Uuidgenerator getUUID(String email) {
    return transaction((em) -> {
      try {
        Query query = em.createNamedQuery("Uuidgenerator.findByEmail", Uuidgenerator.class);
        query.setParameter("email", email);
        query.setParameter("state", FactoryDao.getDao(StateDao.class).Active());
        query.setParameter("type", FactoryDao.getDao(TypeDao.class).Registraion());
        return (Uuidgenerator) query.getSingleResult();
      } catch (NoResultException e) {
        Uuidgenerator uuid = new Uuidgenerator();
        uuid.setEmail(email);
        uuid.setUuid(Util.createUUIDKey());
        uuid.setState(FactoryDao.getDao(StateDao.class).Active());
        uuid.setType(FactoryDao.getDao(TypeDao.class).Registraion());
        uuid.setCreateDate(new Date());
        super.update(uuid);
        return uuid;
      }
    });
  }
}
