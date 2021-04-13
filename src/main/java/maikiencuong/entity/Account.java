package maikiencuong.entity;

import java.util.Set;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Entity
@Table(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(name = "username", columnDefinition = "varchar(50) not null")
	private String username;

	@Setter
	@Column(name = "password", columnDefinition = "varchar(255) not null")
	private String password;

	@Getter
	@Setter
	@Column(name = "enable", nullable = false)
	private boolean enable;

	// --------------------

	@PrePersist
	private void prePersist() {
		enable = true;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@Getter
	@Setter
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Account_Role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Getter
	@Setter
	@JsonIgnore
	@ToString.Exclude
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private User user;

	@Getter
	@Setter
	@JsonIgnore
	@ToString.Exclude
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private Customer customer;

}
