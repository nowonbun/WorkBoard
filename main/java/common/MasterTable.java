package common;

import java.io.Serializable;

public interface MasterTable extends Serializable {
  public String getCode();

  public void setCode(String code);

  public boolean getIsactive();

  public void setIsactive(boolean isactive);

  public String getName();

  public void setName(String name);
}
