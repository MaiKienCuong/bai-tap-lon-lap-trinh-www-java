package maikiencuong.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.Account;

public interface AccountServ {

	public Account findById(Long id);

	public Page<Account> findAll(Pageable pageable);

	public Account add(Account account);

	public Account update(Account account);

	public void delete(Long id);

	public boolean existsByUsername(String username);

	public Optional<Account> findByUsername(String username);

	public boolean existsByEmail(String email);

}
