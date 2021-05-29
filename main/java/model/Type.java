package model;

import java.io.Serializable;
import javax.persistence.*;
import common.MasterTable;
import java.util.List;


@Entity
@Table(name="type")
@NamedQueries({
  @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"), 
  @NamedQuery(name = "Type.findActiveAll", query = "SELECT t FROM Type t WHERE t.isactive=true")
})
public class Type implements MasterTable, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String code;

	private boolean isactive;

	private String name;

	//bi-directional many-to-one association to Uuidgenerator
	@OneToMany(mappedBy="type")
	private List<Uuidgenerator> uuidgenerators;

	public Type() {
	}

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

	public List<Uuidgenerator> getUuidgenerators() {
		return this.uuidgenerators;
	}

	public void setUuidgenerators(List<Uuidgenerator> uuidgenerators) {
		this.uuidgenerators = uuidgenerators;
	}

	public Uuidgenerator addUuidgenerator(Uuidgenerator uuidgenerator) {
		getUuidgenerators().add(uuidgenerator);
		uuidgenerator.setType(this);

		return uuidgenerator;
	}

	public Uuidgenerator removeUuidgenerator(Uuidgenerator uuidgenerator) {
		getUuidgenerators().remove(uuidgenerator);
		uuidgenerator.setType(null);

		return uuidgenerator;
	}

}