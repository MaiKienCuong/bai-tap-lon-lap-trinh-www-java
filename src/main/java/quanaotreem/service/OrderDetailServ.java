package quanaotreem.service;

import java.util.List;

import quanaotreem.entity.OrderDetail;

public interface OrderDetailServ {

	List<OrderDetail> findAllByOrder_Id(Long id);

}
