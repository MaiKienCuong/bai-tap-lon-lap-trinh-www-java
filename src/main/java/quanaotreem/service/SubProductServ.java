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

	public SubProduct findById(Long id);

	public SubProduct add(SubProduct subProduct);

	public void delete(Long id);

	public SubProduct update(SubProduct subProduct);

	public List<SubProduct> findAllByProduct_Id(Long id);

	public List<SubProduct> findAllByProduct_IdAndColor(Long id, String color);

	public List<SubProduct> findAllByProduct_IdAndColorAndSize(Long id, String color, String size);

}
