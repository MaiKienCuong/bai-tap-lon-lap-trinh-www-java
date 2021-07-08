package quanaotreem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import quanaotreem.entity.Account;

public interface AccountServ {

	Account findById(Long id);

	Account findByUsername(String username);

	List<Account> findAll();

	Page<Account> findAll(Pageable pageable);

	Account add(Account account);

	void delete(Long id);

	Account update(Account account);

	boolean existsByUsername(String username);

}
