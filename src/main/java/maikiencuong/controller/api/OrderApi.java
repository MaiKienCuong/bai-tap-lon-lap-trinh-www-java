package maikiencuong.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.OrderDTO;
import maikiencuong.dto.create.OrderCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.entity.Orderr;
import maikiencuong.enumvalue.EnumStatusOrder;
import maikiencuong.handler.MyExcetion;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.OrderServ;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderApi {

	@Autowired
	private OrderServ orderServ;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/orders")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "12") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "orderDate-desc") String[] sort,
			@RequestParam(required = false) EnumStatusOrder[] status) throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Orderr> pageResult;
		if (status != null)
			pageResult = orderServ.findAllByStatusIn(status, pageable);
		else
			pageResult = orderServ.findAll(pageable);

		return ResponseEntity.ok(getMapOrderResult(pageResult));
	}

	@GetMapping("/orders/customer/{id}")
	public ResponseEntity<?> findAllByCustomerId(@RequestParam(defaultValue = "12") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "orderDate-desc") String[] sort,
			@PathVariable("id") Long id) throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Orderr> pageResult = orderServ.findAllByCustomer_Id(id, pageable);

		return ResponseEntity.ok(getMapOrderResult(pageResult));
	}

	@PostMapping("/order")
	public ResponseEntity<?> addOrder(@DTO(OrderCreateDTO.class) Orderr order) {
		Orderr result = orderServ.add(order);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, OrderDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Thêm không thành công"));
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

	private Map<String, Object> getMapOrderResult(Page<Orderr> pageResult) {
		Map<String, Object> map = new HashMap<>();
		List<OrderDTO> list = modelMapper.map(pageResult.getContent(), new TypeToken<List<OrderDTO>>() {
		}.getType());
		map.put("orders", list);
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());

		return map;
	}
}
