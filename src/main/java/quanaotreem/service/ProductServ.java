package quanaotreem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import quanaotreem.entity.Product;

/**
 * cac phuong thuc khong co Annotation @query se duoc JPA tu dong tao ra cau
 * truy van theo ten cua phuong thuc nhung phuong thuc co thm Annotation @query
 * thi chung ta phai chi dinh cau truy van cho no
 * 
 * @author maikiencuong
 *
 */
public interface ProductServ {

	Product add(Product product);

	void delete(Long id);

	Product update(Product product);

	Product findById(Long id);

	Page<Product> findAll(Pageable pageable);
	
	List<Product> findTop8ByOrderByViewsDesc();
	
	List<Product> findAllByMarkerIn(String[] markers);

	Page<Product> findAllByMarkerIn(String[] markers, Pageable pageable);

	Page<Product> findAllByCategory_NameLike(String category, Pageable pageable);

	Page<Product> findAllByNameLikeOrCategory_NameLike(String name, String category, Pageable pageable);

}
