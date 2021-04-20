package maikiencuong.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.entity.OrderDetail;
import maikiencuong.handler.MyExcetion;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.OrderDetailServ;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderDetailApi {

	@Autowired
	private OrderDetailServ orderDetailServ;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/order-detail/order/{id}")
	public ResponseEntity<?> findAllByOrderId(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id-asc") String[] sort,
			@PathVariable("id") Long id) throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<OrderDetail> pageResult = orderDetailServ.findAllByOrder_Id(id, pageable);
		List<OrderDetail> list = pageResult.getContent();
		if (!list.isEmpty())
			return ResponseEntity.ok(list);
		return ResponseEntity.badRequest().body(new MessageResponse("Danh sách trống"));

	}

	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}

	private List<Order> getListSortOrder(String[] sort) throws MyExcetion {
		List<Order> orders = new ArrayList<>();
		try {
			if (sort[0].contains("-")) {
				for (String sortOrder : sort) {
					String[] subSort = sortOrder.split("-");
					orders.add(new Order(getSortDirection(subSort[1]), subSort[0]));
				}
			} else {
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}
		} catch (Exception e) {
			throw new MyExcetion("Lỗi: Vui lòng kiểm tra lại tham số sort");
		}
		return orders;
	}

}
