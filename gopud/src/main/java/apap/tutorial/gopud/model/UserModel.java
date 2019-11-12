package apap.tutorial.gopud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class UserModel implements Serializable{
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	
	@NotNull
	@Size(max= 50)
	@Column(name="username", nullable = false)
	private String username;
	
	@NotNull
	@Lob
	@Column(name ="password", nullable = false)
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="id_role", referencedColumnName ="id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private RoleModel role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}
}
