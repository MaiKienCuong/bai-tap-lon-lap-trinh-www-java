package maikiencuong.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", columnDefinition = "nvarchar(255) not null")
	private String name;

	@Column(name = "identity_card", columnDefinition = "varchar(50)")
	private String identityCard;

	@Column(name = "phone", columnDefinition = "varchar(50)")
	private String phone;

	@Column(name = "email", columnDefinition = "varchar(50)")
	private String email;

	@Column(name = "birthDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private Date birthDate;

	@Column(name = "native_country", columnDefinition = "nvarchar(255)")
	private String nativeCountry;

	@Column(name = "address", columnDefinition = "nvarchar(255)")
	private String address;

	// ------------------

//	@JsonIgnore
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", columnDefinition = "bigint")
	private Account account;

}
