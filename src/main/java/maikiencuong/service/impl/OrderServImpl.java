package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Order;
import maikiencuong.repository.OrderRepo;
import maikiencuong.service.OrderServ;

@Service
public class OrderServImpl implements OrderServ {

	@Autowired
	private OrderRepo orderRepo;

	@Override
	@Transactional
	public Page<Order> findAll(Pageable pageable) {
		return orderRepo.findAll(pageable);
	}

	@Override
	@Transactional
	public Order findById(Long id) {
		Optional<Order> optional = orderRepo.findById(id);
		return optional.isEmpty() ? optional.get() : null;
	}

	@Override
	@Transactional
	public Order add(Order order) {
		return orderRepo.save(order);
	}

	@Override
	@Transactional
	public Order update(Order order) {
		return orderRepo.saveAndFlush(order);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		orderRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Page<Order> findAllByCustomer_Id(Long id, Pageable pageable) {
		return orderRepo.findAllByCustomer_Id(id, pageable);
	}

}
