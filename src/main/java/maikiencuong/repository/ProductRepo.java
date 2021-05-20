package maikiencuong.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Product;

/**
 * The Interface ProductRepo.
 * 
 * <p>
 * Cac phuong thuc trong class nay dat ten theo dung quy dinh (Query method
 * JpaRepository) thi Jpa se tu dong tao ra cau truy van, neu khong thi phai
 * khai bao cau query cho method bang annotation Query<br>
 * Khong can tao class implements interface nay
 * </p>
 * 
 * @Repository danh dau day la class Repository, dung de giao tiep voi database
 */
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	public Page<Product> findAllByCategory_NameLike(String category, Pageable pageable);

	public Page<Product> findAllByNameLikeOrCategory_NameLike(String name, String category, Pageable pageable);

	public Page<Product> findAllByMarkerIn(String[] markers, Pageable pageable);

	public List<Product> findTop10ByOrderByViewsDesc();

}
