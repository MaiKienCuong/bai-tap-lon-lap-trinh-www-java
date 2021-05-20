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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.OrderDTO;
import maikiencuong.dto.create.OrderCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.dto.update.OrderUpdateDTO;
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

	/**
	 * Find all.
	 *
	 * @param size   the size
	 * @param page   the page
	 * @param sort   the sort
	 * @param status the status
	 * @return the response entity
	 * @throws MyExcetion the my excetion
	 */
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

	/**
	 * Find all by customer id.
	 *
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @param id   the id
	 * @return the response entity
	 * @throws MyExcetion the my excetion
	 */
	@GetMapping("/orders/customer/{id}")
	public ResponseEntity<?> findAllByCustomerId(@RequestParam(defaultValue = "12") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "orderDate-desc") String[] sort,
			@PathVariable("id") Long id) throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Orderr> pageResult = orderServ.findAllByCustomer_Id(id, pageable);

		return ResponseEntity.ok(getMapOrderResult(pageResult));
	}

	/**
	 * Add the order.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param newOrderr the new orderr
	 * @return the response entity
	 */
	@PostMapping("/order")
	public ResponseEntity<?> addOrder(@DTO(OrderCreateDTO.class) Orderr newOrderr) {
		Orderr result = orderServ.add(newOrderr);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, OrderDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Thêm hóa đơn không thành công"));
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Orderr findById = orderServ.findById(id);
		if (findById != null)
			return ResponseEntity.ok(modelMapper.map(findById, OrderDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Không tìm thấy hóa đơn"));
	}

	/**
	 * Update status order.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param updateOrderr the update orderr
	 * @return the response entity
	 */
	@PutMapping("/order")
	public ResponseEntity<?> updateStatusOrder(@DTO(OrderUpdateDTO.class) Orderr updateOrderr) {
		EnumStatusOrder statusOrder = updateOrderr.getStatus();
		Orderr existsOrder = orderServ.findById(updateOrderr.getId());
		existsOrder.setStatus(statusOrder);
		Orderr result = orderServ.update(existsOrder);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, OrderDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Cập nhật hóa đơn không thành công"));
	}

	/**
	 * Gets the sort direction.
	 *
	 * @param direction the direction
	 * @return the sort direction
	 */
	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	/**
	 * Gets the list sort order.
	 *
	 * @param sort the sort
	 * @return the list sort order
	 * @throws MyExcetion the my excetion
	 */
	private List<Order> getListSortOrder(String[] sort) throws MyExcetion {
		List<Order> orders = new ArrayList<>();
		try {
			for (int i = 0; i < sort.length; i++) {
				if (sort[i].contains("-")) {
					for (String sortOrder : sort) {
						String[] subSort = sortOrder.split("-");
						orders.add(new Order(getSortDirection(subSort[1]), subSort[0]));
					}
				}
			}
		} catch (Exception e) {
			throw new MyExcetion("Lỗi: Vui lòng kiểm tra lại tham số sort");
		}

		return orders;
	}

	/**
	 * Gets the map order result.
	 *
	 * @param pageResult the page result
	 * @return the map order result
	 */
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
