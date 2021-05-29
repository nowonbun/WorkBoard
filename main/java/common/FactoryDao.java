package common;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import dao.StateDao;
import dao.TypeDao;

public class FactoryDao {
  private static FactoryDao instance = null;
  private Map<Class<?>, AbstractDao<?>> flyweight = null;

  @SuppressWarnings("unchecked")
  public static <T> T getDao(Class<T> clz) {
    try {
      if (instance == null) {
        instance = new FactoryDao();
      }
      if (instance.flyweight == null) {
        instance.flyweight = new HashMap<Class<?>, AbstractDao<?>>();
      }
      if (!instance.flyweight.containsKey(clz)) {
        Constructor<T> constructor = clz.getDeclaredConstructor();
        constructor.setAccessible(true);
        instance.flyweight.put(clz, (AbstractDao<?>) constructor.newInstance());
      }
      return (T) instance.flyweight.get(clz);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  public static void initializeMaster() {
    FactoryDao.getDao(StateDao.class).clear();
    FactoryDao.getDao(TypeDao.class).clear();
  }

  public static void resetMaster() {
    
  }
}
