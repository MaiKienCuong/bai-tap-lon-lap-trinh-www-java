package maikiencuong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.Supplier;

public interface SupplierServ {

	public Supplier findById(Long id);

	public Supplier add(Supplier supplier);

	public void delete(Long id);

	public Supplier update(Supplier supplier);

	public Page<Supplier> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable);

	public Page<Supplier> findAll(Pageable pageable);

}
