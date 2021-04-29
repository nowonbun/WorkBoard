package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "Password.findAll", query = "SELECT p FROM Password p")
public class Password implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private PasswordPK id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

  private String password;

  @ManyToOne
  @JoinColumn(name = "id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "state")
  private State state;

  public Password() {}

  public PasswordPK getId() {
    return this.id;
  }

  public void setId(PasswordPK id) {
    this.id = id;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public State getState() {
    return this.state;
  }

  public void setState(State state) {
    this.state = state;
  }

}
