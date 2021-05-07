package maikiencuong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Image;

/**
 * cac phuong thuc khong co Annotation @query se duoc JPA tu dong tao ra cau
 * truy van theo ten cua phuong thuc nhung phuong thuc co thm Annotation @query
 * thi chung ta phai chi dinh cau truy van cho no
 * 
 * @author maikiencuong
 *
 */
@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

}
