package dao;

import common.AbstractDao;
import model.Password;

public class PasswordDao extends AbstractDao<Password> {

  protected PasswordDao() {
    super(Password.class);
  }

}
