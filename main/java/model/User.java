package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id=:id AND u.state=:state")
})
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idx;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_date")
  private Date createDate;

  private String id;

  @Lob
  private byte[] img;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_update")
  private Date lastUpdate;

  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "state")
  private State state;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Password> passwords;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "company")
  private Company company;

  public User() {}

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

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
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

  public State getStateBean() {
    return this.state;
  }

  public void setStateBean(State state) {
    this.state = state;
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

  public void setCompanyBean(Company company) {
    this.company = company;
  }

}
