package pl.kwi.chrisblog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.kwi.db.abstr.AbstractEntity;

@Entity
@Table(name="user_roles")
public class UserRoleEntity extends AbstractEntity{
	
	
	private static final long serialVersionUID = 1L;
	private String authority;
	private UserEntity user;
	
	
	@Column(name="authority", length=45, nullable=false)
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@ManyToOne
	@JoinColumn(name="user_id")
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
			

}
