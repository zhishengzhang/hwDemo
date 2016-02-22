package zhang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER_DETAILS")
@Data
public class UserDetails {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "uuid")
	@NotBlank(message="uuid is not null")
	private String uuid;

	@Column(name = "email")
	@NotBlank(message="email is not null")
	private String email;

	public UserDetails(){}
	public UserDetails(String uuid2, String email2) {
		uuid = uuid2;
		email = email2;
	}

}
