package dao;

import common.AbstractDao;
import model.Company;

public class CompanyDao extends AbstractDao<Company> {

  protected CompanyDao() {
    super(Company.class);
  }

}
