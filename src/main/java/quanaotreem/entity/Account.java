package quanaotreem.entity;

import java.util.Arrays;
import java.util.HashSet;
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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import quanaotreem.enumvalue.EnumRole;

@Builder
@Entity
@Getter
@Setter
@ToString
@Table(name = "Account")
@AllArgsConstructor
@EqualsAndHashCode(of = { "id", "username" })
@DynamicUpdate
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 60, unique = true)
	private String username;

	@ToString.Exclude
	@Column(nullable = false, length = 255)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private boolean enable;

	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Account_Role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@JsonIgnore
	@ToString.Exclude
	@OneToOne(mappedBy = "account", cascade = { CascadeType.ALL })
	private Customer customer;

	public Account() {
		roles = new HashSet<>(Arrays.asList(new Role(EnumRole.ROLE_CUSTOMER)));
	}

	@PrePersist
	public void prePersist() {
		enable = true;
	}

}
