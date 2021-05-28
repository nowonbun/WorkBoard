package common;

import java.io.Serializable;
import java.util.Date;
import model.State;

public interface TransactionTable extends Serializable {
  public int getIdx();

  public void setIdx(int idx);

  public Date getCreateDate();

  public void setCreateDate(Date createDate);

  public Date getLastUpdate();

  public void setLastUpdate(Date lastUpdate);

  public State getState();

  public void setState(State state);
}
