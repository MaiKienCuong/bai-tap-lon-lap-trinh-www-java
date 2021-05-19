package maikiencuong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Role;
import maikiencuong.enumvalue.EnumRole;

/**
 * The Interface RoleRepo.
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
public interface RoleRepo extends JpaRepository<Role, Long> {

	public Optional<Role> findByName(EnumRole name);

}
