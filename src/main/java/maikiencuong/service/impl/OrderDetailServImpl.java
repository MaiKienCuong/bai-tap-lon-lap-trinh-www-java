package maikiencuong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<OrderDetail> findAllByOrder_Id(Long id) {
		return orderDetailRepo.findAllByOrder_Id(id);
	}

}
