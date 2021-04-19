package maikiencuong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.OrderDetail;

public interface OrderDetailServ {

	public Page<OrderDetail> findAllByOrder_Id(Long id, Pageable pageable);

}
