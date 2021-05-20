package maikiencuong.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.Product;

/**
 * cac phuong thuc khong co Annotation @query se duoc JPA tu dong tao ra cau
 * truy van theo ten cua phuong thuc nhung phuong thuc co thm Annotation @query
 * thi chung ta phai chi dinh cau truy van cho no
 * 
 * @author maikiencuong
 *
 */
public interface ProductServ {

	public Product add(Product product);

	public void delete(Long id);

	public Product update(Product product);

	public Product findById(Long id);

	public Page<Product> findAll(Pageable pageable);
	
	public List<Product> findTop10ByOrderByViewsDesc();

	public Page<Product> findAllByMarkerIn(String[] markers, Pageable pageable);

	public Page<Product> findAllByCategory_NameLike(String category, Pageable pageable);

	public Page<Product> findAllByNameLikeOrCategory_NameLike(String name, String category, Pageable pageable);

}
