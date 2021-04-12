package maikiencuong.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import maikiencuong.entity.Account;
import maikiencuong.entity.Customer;
import maikiencuong.entity.User;

public class AccountDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Getter
	private Long id;

	private String username;

	@JsonIgnore
	private boolean enable;

	@JsonIgnore
	private String password;

	@JsonIgnore
	@Getter
	private User user;

	@JsonIgnore
	@Getter
	private Customer customer;

	private Collection<? extends GrantedAuthority> authorities;

	public AccountDetailsImpl(Long id, String username, boolean enable, String password, Customer customer, User user,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.enable = enable;
		this.password = password;
		this.customer = customer;
		this.user = user;
		this.authorities = authorities;
	}

	public static AccountDetailsImpl build(Account account) {
		List<GrantedAuthority> authorities = account.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new AccountDetailsImpl(account.getId(), account.getUsername(), account.isEnable(), account.getPassword(),
				account.getCustomer(), account.getUser(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDetailsImpl other = (AccountDetailsImpl) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}