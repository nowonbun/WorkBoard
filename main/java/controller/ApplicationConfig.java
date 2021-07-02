package controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import common.FactoryDao;
import dao.CompanyDao;
import dao.MenuDao;
import dao.StateDao;
import dao.UserDao;
import dao.UuidgeneratorDao;

@Configuration
public class ApplicationConfig {
  @Bean(name = "UserDao")
  public UserDao getUserDao() {
    return FactoryDao.getDao(UserDao.class);
  }

  @Bean(name = "UuidgeneratorDao")
  public UuidgeneratorDao getUuidgeneratorDao() {
    return FactoryDao.getDao(UuidgeneratorDao.class);
  }

  @Bean(name = "CompanyDao")
  public CompanyDao getCompanyDao() {
    return FactoryDao.getDao(CompanyDao.class);
  }

  @Bean(name = "StateDao")
  public StateDao getStateDao() {
    return FactoryDao.getDao(StateDao.class);
  }

  @Bean(name = "MenuDao")
  public MenuDao getMenuDao() {
    return FactoryDao.getDao(MenuDao.class);
  }
}
