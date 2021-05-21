package maikiencuong.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Product;
import maikiencuong.entity.SubProduct;
import maikiencuong.repository.ProductRepo;
import maikiencuong.service.ProductServ;
import maikiencuong.service.SubProductServ;

@Service
public class ProductServImpl implements ProductServ {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private SubProductServ subProductServ;

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
	@Transactional
	public void delete(Long id) {
		productRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Product update(Product product) {
		for (Iterator<SubProduct> iterator = product.getSubProducts().iterator(); iterator.hasNext();) {
			SubProduct subProduct = iterator.next();
			if (subProduct.getId() != null)
				subProduct.setCreatedAt(subProductServ.findById(subProduct.getId()).getCreatedAt());
			subProduct.setProduct(product);
		}
		return productRepo.saveAndFlush(product);
	}

	@Override
	@Transactional
	public List<Product> findTop10ByOrderByViewsDesc() {
		return productRepo.findTop10ByOrderByViewsDesc();
	}

	@Override
	@Transactional
	public List<Product> findAllByMarkerIn(String[] markers) {
		return productRepo.findAllByMarkerIn(markers);
	}

}
