import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import model.Company;
import model.Password;
import model.PasswordPK;
import model.State;
import model.User;

public class DatabaseTest {

  private static EntityManagerFactory emf;
  private static EntityManager em;
  private static EntityTransaction transaction;

  @BeforeClass
  public static void init() throws Throwable {
    emf = Persistence.createEntityManagerFactory("WorkBoard");
    em = emf.createEntityManager();
    transaction = em.getTransaction();
  }

  // @Test
  public void insertStateMaster() throws Throwable {
    transaction.begin();
    try {
      State state = new State();
      state.setCode("ACTI");
      state.setName("Active");
      em.persist(state);

      state = new State();
      state.setCode("DELE");
      state.setName("Deleted");
      em.persist(state);

      transaction.commit();
    } catch (Throwable e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw e;
    }
  }

  @Test
  public void connectTest() throws Throwable {
    transaction.begin();
    try {
      Assert.assertTrue("Active".equals(em.find(State.class, "ACTI").getName()));
      Assert.assertTrue("Deleted".equals(em.find(State.class, "DELE").getName()));
      transaction.commit();
    } catch (Throwable e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw e;
    }
  }

  //@Test
  public void insertUserExample() throws Throwable {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("WorkBoard");
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    try {
      State active = em.find(State.class, "ACTI");
      
      Company com = new Company();
      com.setCode("TEST0");
      com.setName("Test company");
      com.setState(active);
      em.persist(com);
      
      User user = new User();
      user.setCompany(com);
      user.setId("nowonbun@gmail.com");
      user.setName("Soonyub.hwang");
      user.setState(active);
      em.persist(user);
      
      Password pw = new Password();
      PasswordPK pk = new PasswordPK();
      //pk.setIdx(idx);
      //pw.setId(id);
      
      transaction.commit();
    } catch (Throwable e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw e;
    }
  }
}
