package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Product;
import maikiencuong.repository.ProductRepo;
import maikiencuong.service.ProductServ;

@Service
public class ProductServImpl implements ProductServ {

	@Autowired
	private ProductRepo productRepo;

	@Override
	@Transactional
	public Product findById(Long id) {
		Optional<Product> optional = productRepo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	@Transactional
	public Page<Product> findAll(Pageable pageable) {
		return productRepo.findAll(pageable);
	}

	@Override
	@Transactional
	public Page<Product> findAllByNameLikeOrCategory_NameLike(String name, String category, Pageable pageable) {
		return productRepo.findAllByNameLikeOrCategory_NameLike("%" + name + "%", "%" + category + "%", pageable);
	}

	@Override
	@Transactional
	public Page<Product> findAllByCategory_NameLike(String category, Pageable pageable) {
		return productRepo.findAllByCategory_NameLike("%" + category + "%", pageable);
	}

	@Override
	@Transactional
	public Page<Product> findAllByMarkerIn(String[] markers, Pageable pageable) {
		return productRepo.findAllByMarkerIn(markers, pageable);
	}

	@Override
	@Transactional
	public Product add(Product product) {
		return productRepo.save(product);
	}

	@Override
	public void delete(Long id) {
		productRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Product update(Product product) {
		return productRepo.saveAndFlush(product);
	}

}
