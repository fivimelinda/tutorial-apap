package apap.tutorial.gopud.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="role")
public class RoleModel implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=50)
	@Column(name="role", nullable = false)
	private String role;
	
	@OneToMany(mappedBy="role", fetch=FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private List<UserModel> userRole;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<UserModel> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserModel> userRole) {
		this.userRole = userRole;
	}

}
