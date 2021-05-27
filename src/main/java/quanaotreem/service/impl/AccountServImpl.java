package quanaotreem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quanaotreem.entity.Account;
import quanaotreem.repository.AccountRepo;
import quanaotreem.service.AccountServ;

@Service
public class AccountServImpl implements AccountServ {

	@Autowired
	private AccountRepo accountRepo;

	@Override
	@Transactional
	public Account findById(Long id) {
		Optional<Account> optional = accountRepo.findById(id);
//		return optional.isPresent()?optional.get():null;
//		2 lenh nay tuong tu nhau
		return optional.orElse(null);
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
		return optional.orElse(null);
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
		return accountRepo.save(account);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		accountRepo.deleteById(id);
	}

	@Override
	public List<Account> findAll() {
		return accountRepo.findAll();
	}

}
