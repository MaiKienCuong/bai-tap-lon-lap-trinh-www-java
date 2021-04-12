package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Account;
import maikiencuong.repository.AccountRepo;
import maikiencuong.service.AccountServ;

@Service
public class AccountServImpl implements AccountServ {

	@Autowired
	private AccountRepo accountRepo;

	@Override
	@Transactional
	public Account findById(Long id) {
		Optional<Account> optional = accountRepo.findById(id);
		return !optional.isEmpty() ? optional.get() : null;
	}

	@Override
	@Transactional
	public boolean existsByUsername(String username) {
		return accountRepo.existsByUsername(username);
	}

	@Override
	@Transactional
	public Optional<Account> findByUsername(String username) {
		return accountRepo.findByUsername(username);
	}

	@Override
	@Transactional
	public Account add(Account account) {
		return accountRepo.save(account);
	}

}
