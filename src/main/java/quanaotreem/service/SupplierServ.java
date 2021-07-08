package quanaotreem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import quanaotreem.entity.Supplier;

public interface SupplierServ {

	Supplier findById(Long id);

	Supplier add(Supplier supplier);

	void delete(Long id);

	Supplier update(Supplier supplier);

	List<Supplier> findAllByNameLikeOrPhoneLike(String name, String phone);

	Page<Supplier> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable);

	Page<Supplier> findAll(Pageable pageable);

	List<Supplier> findAll();

}
