package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	@Transactional
	public boolean existsByUsername(String username) {
		return accountRepo.existsByUsername(username);
	}

	@Override
	@Transactional
	public Account findByUsername(String username) {
		Optional<Account> optional = accountRepo.findByUsername(username);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	@Transactional
	public Account add(Account account) {
		return accountRepo.save(account);
	}

	@Override
	@Transactional
	public Page<Account> findAll(Pageable pageable) {
		return accountRepo.findAll(pageable);
	}

	@Override
	@Transactional
	public Account update(Account account) {
		return accountRepo.saveAndFlush(account);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		accountRepo.deleteById(id);
	}

}
