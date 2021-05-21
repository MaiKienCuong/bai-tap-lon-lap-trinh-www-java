package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Account;
import maikiencuong.repository.AccountRepo;

/**
 * The Class AccountDetailsServiceImpl.
 * 
 * <p>
 * Neu xac thuc bang jdbc thi spring mac dinh se lay thong tin user va
 * authorities trong 2 bang do la bang users(username, password, enable) va bang
 * authorities(username, authority) bang phuong thuc loadUserByUsername()
 * </p>
 * 
 * <p>
 * De spring hieu duoc thong tin cua user nam trong bang nao, va authorities nam
 * trong bang nao, thi ta phai implements lai phuong thuc loadUserByUsername
 * trong interface UserDetailsService, phuong thuc nay tra ve doi tuong
 * UserDetails
 * </p>
 * 
 */
@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountRepo accountRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> optional = accountRepo.findByUsername(username);
		if (optional.isPresent()) {
			return AccountDetailsImpl.build(optional.get());
		} else
			throw new UsernameNotFoundException("Không thể xác thực được tài khoản");
	}

}