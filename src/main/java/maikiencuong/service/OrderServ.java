package maikiencuong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.Order;

public interface OrderServ {

	public Order findById(Long id);

	public Order add(Order order);

	public Order update(Order order);

	public void delete(Long id);

	public Page<Order> findAll(Pageable pageable);

}
