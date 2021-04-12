package maikiencuong.service;

import java.util.Optional;

import maikiencuong.entity.Account;

public interface AccountServ {

	public Account findById(Long id);

	public Account add(Account account);

	public boolean existsByUsername(String username);

	public Optional<Account> findByUsername(String username);

}
