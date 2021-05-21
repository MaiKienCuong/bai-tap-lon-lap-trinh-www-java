package maikiencuong.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import maikiencuong.entity.Account;

/**
 * The Class AccountDetailsImpl.
 * 
 * <p>
 * Class nay ke thua {@link UserDetails} de chua thong tin ve account va
 * authorities, se duoc tra ve boi phuong thuc loadUserByUsername
 * </p>
 */
@EqualsAndHashCode(of = { "account" })
public class AccountDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Getter
	private Account account;

	private Collection<? extends GrantedAuthority> authorities;

	public AccountDetailsImpl(Account account, Collection<? extends GrantedAuthority> authorities) {
		this.account = account;
		this.authorities = authorities;
	}

	public static AccountDetailsImpl build(Account account) {
		List<GrantedAuthority> authorities = account.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new AccountDetailsImpl(account, authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
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
		return account.isEnable();
	}

}