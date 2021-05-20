package maikiencuong.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.Account;

public interface AccountServ {

	public Account findById(Long id);

	public Page<Account> findAll(Pageable pageable);
	
	public List<Account> findAll();

	public Account add(Account account);

	public Account update(Account account);

	public void delete(Long id);

	public boolean existsByUsername(String username);

	public Account findByUsername(String username);

}
