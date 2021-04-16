package maikiencuong.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.entity.Customer;
import maikiencuong.entity.OrderDetail;
import maikiencuong.model.request.OrderModel;
import maikiencuong.service.CustomerServ;
import maikiencuong.service.OrderServ;
import maikiencuong.service.SubProductServ;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

	@Autowired
	private OrderServ orderServ;
	
	@Autowired
	private CustomerServ customerServ;
	
	@Autowired
	private SubProductServ subProductServ;

	@RequestMapping("/orders")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id-asc") String[] sort) {

		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<maikiencuong.entity.Order> pageResult = orderServ.findAll(pageable);
		Map<String, Object> map = getMapOrderResult(pageResult);
		return ResponseEntity.ok(map);
	}

	@PostMapping("/order")
	public ResponseEntity<?> addOrder(@RequestBody OrderModel orderModel) {
		Customer customer = customerServ.findById(orderModel.getCustomer().getId());
		List<OrderDetail> orderDetails=new ArrayList<OrderDetail>();
		
		maikiencuong.entity.Order order=maikiencuong.entity.Order.builder()
				.customer(customer)
				.shipAddress(orderModel.getShipAddress())
				.paymentMethod(orderModel.getPaymentMethod())
				.build();
		
		orderModel.getOrderDetails().forEach(o->{
			OrderDetail detail=OrderDetail.builder()
					.quantity(o.getQuantity())
					.price(o.getPrice())
					.order(order)
					.subProduct(subProductServ.findById(o.getSubProduct().getId()))
					.build();
			orderDetails.add(detail);
		});
		order.setOrderDetails(orderDetails);
		maikiencuong.entity.Order result = orderServ.add(order);
		return ResponseEntity.ok(result);
	}

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}

	private List<Order> getListSortOrder(String[] sort) {
		List<Order> orders = new ArrayList<>();
		if (sort[0].contains("-")) {
			// neu sort tren nhieu hon 1 field
			// ?sort=id,desc&sort=title,asc
			for (String sortOrder : sort) {
				String[] subSort = sortOrder.split("-");
				orders.add(new Order(getSortDirection(subSort[1]), subSort[0]));
			}
		} else {
			// neu chi sort tren 1 field
			// ?sort=id,desc
			orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		}
		return orders;
	}

	private Map<String, Object> getMapOrderResult(Page<maikiencuong.entity.Order> pageResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("orders", pageResult.getContent());
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());
		return map;
	}
}
