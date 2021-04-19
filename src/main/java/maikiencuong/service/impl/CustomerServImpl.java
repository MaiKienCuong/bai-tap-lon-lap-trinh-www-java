package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Customer;
import maikiencuong.repository.CustomerRepo;
import maikiencuong.service.CustomerServ;

@Service
public class CustomerServImpl implements CustomerServ {

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	@Transactional
	public Customer findById(Long id) {
		Optional<Customer> optional = customerRepo.findById(id);
		return !optional.isEmpty() ? optional.get() : null;
	}

	@Override
	@Transactional
	public Customer add(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		customerRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Customer update(Customer customer) {
		return customerRepo.saveAndFlush(customer);
	}

	@Override
	@Transactional
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepo.findAll(pageable);
	}

	@Override
	@Transactional
	public Page<Customer> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable) {
		return customerRepo.findAllByNameLikeOrPhoneLike(name, phone, pageable);
	}

	@Override
	@Transactional
	public boolean existsByEmail(String email) {
		return customerRepo.existsByEmail(email);
	}

	@Override
	@Transactional
	public Customer findByEmail(String email) {
		Optional<Customer> optional = customerRepo.findByEmail(email);
		return optional.isPresent() ? optional.get() : null;
	}

}
