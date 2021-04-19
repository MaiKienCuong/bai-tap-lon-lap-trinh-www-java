package maikiencuong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.OrderDetail;
import maikiencuong.repository.OrderDetailRepo;
import maikiencuong.service.OrderDetailServ;

@Service
public class OrderDetailServImpl implements OrderDetailServ {

	@Autowired
	private OrderDetailRepo orderDetailRepo;

	@Override
	@Transactional
	public Page<OrderDetail> findAllByOrder_Id(Long id, Pageable pageable) {
		return orderDetailRepo.findAllByOrder_Id(id, pageable);
	}

}
