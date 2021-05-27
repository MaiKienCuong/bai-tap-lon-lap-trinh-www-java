package quanaotreem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quanaotreem.entity.OrderDetail;
import quanaotreem.repository.OrderDetailRepo;
import quanaotreem.service.OrderDetailServ;

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
