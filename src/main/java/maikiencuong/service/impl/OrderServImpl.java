package maikiencuong.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.OrderDetail;
import maikiencuong.entity.Orderr;
import maikiencuong.entity.SubProduct;
import maikiencuong.repository.OrderRepo;
import maikiencuong.service.OrderServ;
import maikiencuong.service.SubProductServ;

@Service
public class OrderServImpl implements OrderServ {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private SubProductServ subProductServ;

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
		Orderr result = orderRepo.save(order);
		List<OrderDetail> orderDetails = order.getOrderDetails();
		for (Iterator<?> iterator = orderDetails.iterator(); iterator.hasNext();) {
			OrderDetail odd = (OrderDetail) iterator.next();
			SubProduct subProduct = odd.getSubProduct();
			subProduct.setInventory(subProduct.getInventory() - odd.getQuantity());
			subProductServ.update(subProduct);
		}
		return result;
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
		return orderRepo.findAllByCustomer_IdOrderByOrderDateDesc(id, pageable);
	}

}
