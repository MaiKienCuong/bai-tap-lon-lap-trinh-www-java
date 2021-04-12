package maikiencuong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Product;

/**
 * cac phuong thuc khong co Annotation @query se duoc JPA tu dong tao ra cau
 * truy van theo ten cua phuong thuc nhung phuong thuc co thm Annotation @query
 * thi chung ta phai chi dinh cau truy van cho no
 * 
 * @author maikiencuong
 *
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	public Page<Product> findAllByCategoryLike(String category, Pageable pageable);

	public Page<Product> findAllByNameLikeOrCategoryLike(String name, String category, Pageable pageable);

	public Page<Product> findAllByMarkerIn(String[] markers, Pageable pageable);

}
