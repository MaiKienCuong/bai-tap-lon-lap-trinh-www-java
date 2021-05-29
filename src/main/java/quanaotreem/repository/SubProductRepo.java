package quanaotreem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quanaotreem.entity.SubProduct;

/**
 * The Interface SubProductRepo.
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
public interface SubProductRepo extends JpaRepository<SubProduct, Long> {

	public List<SubProduct> findAllByProduct_IdOrderBySize(Long id);

	public List<SubProduct> findAllByProduct_IdAndColorByOrderBySize(Long id, String color);

	public List<SubProduct> findAllByProduct_IdAndColorAndSize(Long id, String color, String size);

}
