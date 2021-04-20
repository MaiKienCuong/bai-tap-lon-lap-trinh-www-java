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
			throw new UsernameNotFoundException("Đăng nhập không thành công");
	}

}