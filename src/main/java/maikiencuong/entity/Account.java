package maikiencuong.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Entity
@Getter
@Setter
@Table(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", columnDefinition = "varchar(50) not null", unique = true)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password", columnDefinition = "varchar(255) not null")
	private String password;

	@Column(name = "enable", nullable = false)
	private boolean enable;

	// --------------------

	@PrePersist
	private void prePersist() {
		enable = true;
	}

//	@ToString.Exclude
	@JsonProperty(access = Access.READ_ONLY)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "Account_Role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@JsonIgnore
	@ToString.Exclude
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private Customer customer;

}
