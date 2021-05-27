package quanaotreem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import quanaotreem.entity.Customer;

public interface CustomerServ {

	public Customer findById(Long id);

	public Page<Customer> findAll(Pageable pageable);

	public Page<Customer> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable);

	public Customer add(Customer customer);

	public void delete(Long id);

	public Customer update(Customer customer);

	public boolean existsByEmail(String email);

	public Customer findByEmail(String email);

}
