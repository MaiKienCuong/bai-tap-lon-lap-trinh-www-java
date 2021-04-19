package maikiencuong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.SubProduct;

/**
 * cac phuong thuc khong co Annotation @query se duoc JPA tu dong tao ra cau
 * truy van theo ten cua phuong thuc nhung phuong thuc co thm Annotation @query
 * thi chung ta phai chi dinh cau truy van cho no
 * 
 * @author maikiencuong
 *
 */
@Repository
public interface SubProductRepo extends JpaRepository<SubProduct, Long> {

	public List<SubProduct> findAllByProduct_Id(Long id);

	public List<SubProduct> findAllByProduct_IdAndColor(Long id, String color);

	public List<SubProduct> findAllByProduct_IdAndColorAndSize(Long id, String color, String size);

}
