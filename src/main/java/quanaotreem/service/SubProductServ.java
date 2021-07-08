package quanaotreem.service;

import java.util.List;

import quanaotreem.entity.SubProduct;

/**
 * cac phuong thuc khong co Annotation @query se duoc JPA tu dong tao ra cau
 * truy van theo ten cua phuong thuc nhung phuong thuc co thm Annotation @query
 * thi chung ta phai chi dinh cau truy van cho no
 * 
 * @author maikiencuong
 *
 */
public interface SubProductServ {

	SubProduct findById(Long id);

	SubProduct add(SubProduct subProduct);

	void delete(Long id);

	SubProduct update(SubProduct subProduct);

	List<SubProduct> findAllByProduct_Id(Long id);

	List<SubProduct> findAllByProduct_IdAndColor(Long id, String color);

	List<SubProduct> findAllByProduct_IdAndColorAndSize(Long id, String color, String size);

}
