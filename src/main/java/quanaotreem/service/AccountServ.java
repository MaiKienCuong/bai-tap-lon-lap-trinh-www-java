package quanaotreem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import quanaotreem.entity.Account;

public interface AccountServ {

	public Account findById(Long id);

	public Account findByUsername(String username);

	public List<Account> findAll();

	public Page<Account> findAll(Pageable pageable);

	public Account add(Account account);

	public void delete(Long id);

	public Account update(Account account);

	public boolean existsByUsername(String username);

}
