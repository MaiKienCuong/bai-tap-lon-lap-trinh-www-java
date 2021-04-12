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

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", columnDefinition = "varchar(50) not null")
	private String username;

	@JsonIgnore
	@Column(name = "password", columnDefinition = "varchar(255) not null")
	private String password;

	@Column(name = "enable", nullable = false)
	private boolean enable;

	// --------------------

	@PrePersist
	private void prePersist() {
		enable = true;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Account_Role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@JsonIgnore
	@ToString.Exclude
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private User user;

	@JsonIgnore
	@ToString.Exclude
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
	private Customer customer;

}
