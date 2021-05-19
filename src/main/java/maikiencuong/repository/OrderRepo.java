package maikiencuong.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Orderr;
import maikiencuong.enumvalue.EnumStatusOrder;

/**
 * The Interface OrderRepo.
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
public interface OrderRepo extends JpaRepository<Orderr, Long> {

	public Page<Orderr> findAllByCustomer_Id(Long id, Pageable pageable);

	public List<Orderr> findAllByCustomer_Id(Long id);

	public Page<Orderr> findAllByStatusIn(EnumStatusOrder[] status, Pageable pageable);

}
