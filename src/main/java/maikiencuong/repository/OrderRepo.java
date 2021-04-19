package maikiencuong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

	public Page<Order> findAllByCustomer_Id(Long id, Pageable pageable);
	
}
