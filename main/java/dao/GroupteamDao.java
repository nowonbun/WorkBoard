package dao;

import java.util.List;
import javax.persistence.NoResultException;
import common.AbstractDao;
import model.Company;
import model.Groupteam;

public class GroupteamDao extends AbstractDao<Groupteam> {

  protected GroupteamDao() {
    super(Groupteam.class);
  }

  public List<Groupteam> findAll() {
    return transaction((em) -> {
      try {
        var query = em.createNamedQuery("Groupteam.findAll", Groupteam.class);
        return (List<Groupteam>) query.getResultList();
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public Groupteam findById(Company company, int idx) {
    return transaction((em) -> {
      try {
        var query = em.createNamedQuery("Groupteam.findById", Groupteam.class);
        query.setParameter("company", company);
        query.setParameter("idx", idx);
        return (Groupteam) query.getSingleResult();
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public Groupteam findByName(Company company, String name) {
    return transaction((em) -> {
      try {
        var query = em.createNamedQuery("Groupteam.findByName", Groupteam.class);
        query.setParameter("company", company);
        query.setParameter("name", name);
        return (Groupteam) query.getSingleResult();
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public Groupteam findByNameWithoutId(Company company, int idx, String name) {
    return transaction((em) -> {
      try {
        var query = em.createNamedQuery("Groupteam.findByNameWithoutId", Groupteam.class);
        query.setParameter("company", company);
        query.setParameter("idx", idx);
        query.setParameter("name", name);
        return (Groupteam) query.getSingleResult();
      } catch (NoResultException e) {
        return null;
      }
    });
  }

  public List<Groupteam> findAllByCompany(Company company) {
    return transaction((em) -> {
      try {
        var query = em.createNamedQuery("Groupteam.findAllByCompany", Groupteam.class);
        query.setParameter("company", company);
        return (List<Groupteam>) query.getResultList();
      } catch (NoResultException e) {
        return null;
      }
    });
  }
}
