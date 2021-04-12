package maikiencuong.service;

import java.util.Set;

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
public interface SubProductServ {

	public SubProduct findById(Long id);

	public SubProduct add(SubProduct subProduct);

	public void delete(Long id);

	public SubProduct update(SubProduct subProduct);

	public Set<InvenColorSize> sizeById(Long id);

	public Set<InvenColorSize> colorById(Long id);

	public Set<InvenColorSize> inventoryAndSizeByIdAndColor(Long id, String color);

	public Set<InvenColorSize> invenColorSizeByIdAndColorAndSize(Long id, String color, String size);

}
