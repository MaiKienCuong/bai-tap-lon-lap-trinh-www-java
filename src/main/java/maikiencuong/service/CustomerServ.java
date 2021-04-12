package maikiencuong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.Customer;

public interface CustomerServ {

	public Customer findById(Long id);

	public Customer add(Customer customer);

	public void delete(Long id);

	public Customer update(Customer customer);

	public Page<Customer> findAllByNameLike(String name, Pageable pageable);

	public Page<Customer> findAll(Pageable pageable);

	public Page<Customer> findAllByPhoneLike(String phone, Pageable pageable);

	public Page<Customer> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable);

}
