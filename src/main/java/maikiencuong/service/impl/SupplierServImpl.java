package maikiencuong.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Supplier;
import maikiencuong.repository.SupplierRepo;

@Service
public class SupplierServImpl implements maikiencuong.service.SupplierServ {

	@Autowired
	private SupplierRepo supplierRepo;

	@Override
	@Transactional
	public Supplier findById(Long id) {
		Optional<Supplier> optional = supplierRepo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	@Transactional
	public Supplier add(Supplier supplier) {
		return supplierRepo.save(supplier);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		supplierRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Supplier update(Supplier supplier) {
		return supplierRepo.saveAndFlush(supplier);
	}

	@Override
	@Transactional
	public Page<Supplier> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable) {
		return supplierRepo.findAllByNameLikeOrPhoneLike("%" + name + "%", "%" + phone + "%", pageable);
	}

	@Override
	@Transactional
	public Page<Supplier> findAll(Pageable pageable) {
		return supplierRepo.findAll(pageable);
	}

	@Override
	@Transactional
	public List<Supplier> findAllByNameLikeOrPhoneLike(String name, String phone) {
		return supplierRepo.findAllByNameLikeOrPhoneLike("%" + name + "%", "%" + phone + "%");
	}

	@Override
	@Transactional
	public List<Supplier> findAll() {
		return supplierRepo.findAll();
	}

}
