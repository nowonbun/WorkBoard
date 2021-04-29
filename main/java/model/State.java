package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "State.findAll", query = "SELECT s FROM State s")
public class State implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String code;

  private boolean isactive;

  private String name;

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

}
