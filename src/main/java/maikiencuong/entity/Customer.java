package maikiencuong.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Customer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", columnDefinition = "nvarchar(255) not null")
	private String name;

	@Column(name = "phone", columnDefinition = "varchar(50) not null")
	private String phone;

	@Column(name = "email", columnDefinition = "varchar(50) not null", unique = true)
	private String email;

	@Column(name = "address", columnDefinition = "nvarchar(255)")
	private String address;

	@JsonIgnore
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", columnDefinition = "bigint")
	private Account account;

}
