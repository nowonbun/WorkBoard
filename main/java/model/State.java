package model;

import java.io.Serializable;
import javax.persistence.*;
import common.MasterTable;
import java.util.List;

@Entity
@NamedQueries({
  @NamedQuery(name = "State.findAll", query = "SELECT s FROM State s"), 
  @NamedQuery(name = "State.findActiveAll", query = "SELECT s FROM State s WHERE s.isactive=true")
})
public class State implements MasterTable, Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String code;

  private boolean isactive;

  private String name;

  @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Company> companies;

  @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Password> passwords;

  @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<User> users;

  @OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Uuidgenerator> registrations;

  public State() {}

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public boolean getIsactive() {
    return this.isactive;
  }

  public void setIsactive(boolean isactive) {
    this.isactive = isactive;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Company> getCompanies() {
    return this.companies;
  }

  public void setCompanies(List<Company> companies) {
    this.companies = companies;
  }

  public Company addCompany(Company company) {
    getCompanies().add(company);
    company.setState(this);

    return company;
  }

  public Company removeCompany(Company company) {
    getCompanies().remove(company);
    company.setState(null);

    return company;
  }

  public List<Password> getPasswords() {
    return this.passwords;
  }

  public void setPasswords(List<Password> passwords) {
    this.passwords = passwords;
  }

  public Password addPassword(Password password) {
    getPasswords().add(password);
    password.setState(this);

    return password;
  }

  public Password removePassword(Password password) {
    getPasswords().remove(password);
    password.setState(null);

    return password;
  }

  public List<User> getUsers() {
    return this.users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public User addUser(User user) {
    getUsers().add(user);
    user.setStateBean(this);

    return user;
  }

  public User removeUser(User user) {
    getUsers().remove(user);
    user.setStateBean(null);

    return user;
  }

  public List<Uuidgenerator> getRegistrations() {
    return this.registrations;
  }

  public void setRegistrations(List<Uuidgenerator> registrations) {
    this.registrations = registrations;
  }

  public Uuidgenerator addRegistration(Uuidgenerator registration) {
    getRegistrations().add(registration);
    registration.setState(this);

    return registration;
  }

  public Uuidgenerator removeRegistration(Uuidgenerator registration) {
    getRegistrations().remove(registration);
    registration.setState(null);

    return registration;
  }

}
