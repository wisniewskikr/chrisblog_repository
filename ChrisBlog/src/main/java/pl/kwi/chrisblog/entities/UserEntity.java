package pl.kwi.chrisblog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pl.kwi.db.abstr.AbstractEntity;

@Entity
@Table(name="users")
public class UserEntity extends AbstractEntity{
	
	
	private String name;
	private String password;
	private Boolean enabled;

	
	@Column(name="username", length=45, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="password", length=45, nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="enabled", nullable=false)
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
		

}
