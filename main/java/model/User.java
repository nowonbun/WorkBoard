package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

  @Lob
  private byte[] img;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_update")
  private Date lastUpdate;

  private String name;

  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
  private List<Password> passwords;

  @ManyToOne
  @JoinColumn(name = "company")
  private Company company;

  @ManyToOne
  @JoinColumn(name = "state")
  private State state;

  public User() {}

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public byte[] getImg() {
    return this.img;
  }

  public void setImg(byte[] img) {
    this.img = img;
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

  public List<Password> getPasswords() {
    return this.passwords;
  }

  public void setPasswords(List<Password> passwords) {
    this.passwords = passwords;
  }

  public Password addPassword(Password password) {
    getPasswords().add(password);
    password.setUser(this);

    return password;
  }

  public Password removePassword(Password password) {
    getPasswords().remove(password);
    password.setUser(null);

    return password;
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
