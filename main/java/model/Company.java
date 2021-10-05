package model;

import javax.persistence.*;
import common.TransactionTable;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries(@NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"))
public class Company implements TransactionTable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idx;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_update")
  private Date lastUpdate;

  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "state")
  private State state;

  @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<User> users;

  @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Groupteam> groups;

  public Company() {}

  public int getIdx() {
    return this.idx;
  }

  public void setIdx(int idx) {
    this.idx = idx;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getLastUpdate() {
    return this.lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public State getState() {
    return this.state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public List<User> getUsers() {
    return this.users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public User addUser(User user) {
    getUsers().add(user);
    user.setCompanyBean(this);

    return user;
  }

  public User removeUser(User user) {
    getUsers().remove(user);
    user.setCompanyBean(null);

    return user;
  }

  public List<Groupteam> getGroups() {
    return this.groups;
  }

  public void setGroups(List<Groupteam> groups) {
    this.groups = groups;
  }

  public Groupteam addGroup(Groupteam group) {
    getGroups().add(group);
    group.setCompany(this);

    return group;
  }

  public Groupteam removeGroup(Groupteam group) {
    getGroups().remove(group);
    group.setCompany(null);

    return group;
  }
}
