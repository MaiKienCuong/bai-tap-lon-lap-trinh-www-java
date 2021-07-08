package quanaotreem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import quanaotreem.entity.Customer;

public interface CustomerServ {

	Customer findById(Long id);

	Page<Customer> findAll(Pageable pageable);

	Page<Customer> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable);

	Customer add(Customer customer);

	void delete(Long id);

	Customer update(Customer customer);

	boolean existsByEmail(String email);

	Customer findByEmail(String email);

}
