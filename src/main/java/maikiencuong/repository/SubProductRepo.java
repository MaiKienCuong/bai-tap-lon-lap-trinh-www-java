package maikiencuong.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import maikiencuong.dto.SizeColorInventory;
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

	@Query("select new maikiencuong.dto.SizeColorInventory(s.size, -1) from SubProduct s where s.product.id=:id")
	public Set<SizeColorInventory> sizeById(@Param("id") Long id);

	@Query("select new maikiencuong.dto.SizeColorInventory(s.color) from SubProduct s where s.product.id=:id")
	public Set<SizeColorInventory> colorById(@Param("id") Long id);

	@Query("select new maikiencuong.dto.SizeColorInventory(s.size, s.inventory) from SubProduct s where s.product.id=:id and s.color=:color")
	public Set<SizeColorInventory> inventoryAndSizeByIdAndColor(@Param("id") Long id, @Param("color") String color);

	@Query("select new maikiencuong.dto.SizeColorInventory(s.color, s.size, s.inventory) from SubProduct s where s.product.id=:id and s.color=:color and s.size=:size")
	public Set<SizeColorInventory> invenColorSizeByIdAndColorAndSize(@Param("id") Long id, @Param("color") String color,
			@Param("size") String size);

}
