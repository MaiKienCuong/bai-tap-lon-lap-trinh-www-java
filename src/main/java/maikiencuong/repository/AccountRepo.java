package maikiencuong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Account;

/**
 * cac phuong thuc trong class nay phai dat ten theo dung quy dinh jpa se tu
 * dong tao ra cau truy van, chung ta khong can implements cac phuong thuc nay
 * 
 * @author maikiencuong
 *
 */
@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

	public boolean existsByUsername(String username);

	public Account findByUsername(String username);

}
