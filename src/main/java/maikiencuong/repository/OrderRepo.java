package maikiencuong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

}
