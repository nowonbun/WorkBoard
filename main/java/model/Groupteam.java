package model;

import javax.persistence.*;
import common.TransactionTable;
import java.util.Date;

@Entity
@NamedQueries({
  @NamedQuery(name = "Groupteam.findAll", query = "SELECT g FROM Groupteam g"),
  @NamedQuery(name = "Groupteam.findById", query = "SELECT g FROM Groupteam g WHERE g.company=:company AND g.idx=:idx"),
  @NamedQuery(name = "Groupteam.findByName", query = "SELECT g FROM Groupteam g WHERE g.company=:company AND g.name=:name"),
  @NamedQuery(name = "Groupteam.findByNameWithoutId", query = "SELECT g FROM Groupteam g WHERE g.company=:company AND g.name=:name AND g.idx!=:idx"),
  @NamedQuery(name = "Groupteam.findAllByCompany", query = "SELECT g FROM Groupteam g WHERE g.company=:company")
})
public class Groupteam implements TransactionTable {
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
  @JoinColumn(name = "company")
  private Company company;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "state")
  private State state;

  public Groupteam() {}

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

  public Company getCompany() {
    return this.company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public State getState() {
    return this.state;
  }

  public void setState(State state) {
    this.state = state;
  }

}
