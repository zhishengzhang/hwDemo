package zhang.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="ADMIN_USERS")
@Data
public class AdminUser {
	
	@Id
	@GeneratedValue
	private Integer id;
		
	private String login;
	
	private String password;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROLES",
		joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
	)
	private AdminRole role;

}
