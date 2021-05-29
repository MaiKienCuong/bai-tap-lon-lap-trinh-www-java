package quanaotreem.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quanaotreem.entity.Product;
import quanaotreem.entity.SubProduct;
import quanaotreem.repository.ProductRepo;
import quanaotreem.service.ProductServ;
import quanaotreem.service.SubProductServ;

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
		return optional.orElse(null);
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
		return productRepo.save(product);
	}

	/**
	 * Find top 8 by order by views desc.
	 * 
	 * <p>
	 * Khi goi phai them @Transactional
	 * </p>
	 *
	 * @return the list
	 */
	@Override
//	@Transactional
	public List<Product> findTop8ByOrderByViewsDesc() {
		return productRepo.findTop8ByOrderByViewsDesc();
	}

	/**
	 * Find all by marker in.
	 *
	 * <p>
	 * Khi goi phai them @Transactional
	 * </p>
	 * 
	 * @param markers the markers
	 * @return the list
	 */
	@Override
//	@Transactional
	public List<Product> findAllByMarkerIn(String[] markers) {
		return productRepo.findAllByMarkerIn(markers);
	}

}
