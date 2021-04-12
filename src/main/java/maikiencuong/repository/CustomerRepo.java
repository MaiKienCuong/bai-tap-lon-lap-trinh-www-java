package maikiencuong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	public Page<Customer> findAllByNameLike(String name, Pageable pageable);

	public Page<Customer> findAllByPhoneLike(String phone, Pageable pageable);
	
	public Page<Customer> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable);

}
