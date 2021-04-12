package maikiencuong.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.InvenColorSize;
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

	@Query(value = "select s.invenColorSize from SubProduct s where s.product.id=:id")
	public Set<InvenColorSize> sizeById(@Param("id") Long id);

	@Query(value = "select s.invenColorSize from SubProduct s where s.product.id=:id")
	public Set<InvenColorSize> colorById(@Param("id") Long id);

	@Query(value = "select s.invenColorSize from SubProduct s where s.product.id=:id and s.invenColorSize.color=:color")
	public Set<InvenColorSize> inventoryAndSizeByIdAndColor(@Param("id") Long id, @Param("color") String color);

	@Query(value = "select s.invenColorSize from SubProduct s where s.product.id=:id and s.invenColorSize.color=:color and s.invenColorSize.size=:size")
	public Set<InvenColorSize> invenColorSizeByIdAndColorAndSize(@Param("id") Long id, @Param("color") String color,
			@Param("size") String size);

}
