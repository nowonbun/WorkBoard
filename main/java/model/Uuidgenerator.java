package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
  @NamedQuery(name = "Uuidgenerator.findAll", query = "SELECT u FROM Uuidgenerator u"),
  @NamedQuery(name = "Uuidgenerator.findByEmail", query = "SELECT u FROM Uuidgenerator u where u.email=:email and u.state!=:state")
})

public class Uuidgenerator implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idx;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

  private String email;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_update")
  private Date lastUpdate;

  private String uuid;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "state")
  private State state;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "type")
  private Type type;

  public Uuidgenerator() {}

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

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getLastUpdate() {
    return this.lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public String getUuid() {
    return this.uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public State getState() {
    return this.state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public Type getType() {
    return this.type;
  }

  public void setType(Type type) {
    this.type = type;
  }

}
