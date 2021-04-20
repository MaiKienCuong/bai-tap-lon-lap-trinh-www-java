package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Orderr;
import maikiencuong.repository.OrderRepo;
import maikiencuong.service.OrderServ;

@Service
public class OrderServImpl implements OrderServ {

	@Autowired
	private OrderRepo orderRepo;

	@Override
	@Transactional
	public Page<Orderr> findAll(Pageable pageable) {
		return orderRepo.findAll(pageable);
	}

	@Override
	@Transactional
	public Orderr findById(Long id) {
		Optional<Orderr> optional = orderRepo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	@Transactional
	public Orderr add(Orderr order) {
		return orderRepo.save(order);
	}

	@Override
	@Transactional
	public Orderr update(Orderr order) {
		return orderRepo.saveAndFlush(order);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		orderRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Page<Orderr> findAllByCustomer_Id(Long id, Pageable pageable) {
		return orderRepo.findAllByCustomer_Id(id, pageable);
	}

}
