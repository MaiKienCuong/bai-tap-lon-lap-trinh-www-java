package maikiencuong.service;

import java.util.List;

import maikiencuong.entity.OrderDetail;

public interface OrderDetailServ {

	public List<OrderDetail> findAllByOrder_Id(Long id);

}
