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

import maikiencuong.dto.CustomerDTO;
import maikiencuong.dto.create.CustomerCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.dto.update.CustomerUpdateDTO;
import maikiencuong.entity.Customer;
import maikiencuong.handler.MyExcetion;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.CustomerServ;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CustomerApi {

	@Autowired
	private CustomerServ customerServ;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Find all.
	 *
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @return the response entity
	 * @throws MyExcetion the my excetion
	 */
	@GetMapping("/customers")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "12") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort)
			throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Customer> pageResult = customerServ.findAll(pageable);

		return ResponseEntity.ok(getMapCustomerResult(pageResult));
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Customer result = customerServ.findById(id);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CustomerDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Không tìm thấy khách hàng nào"));
	}

	/**
	 * Add the customer.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param newCustomer the new customer
	 * @return the response entity
	 */
	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@DTO(CustomerCreateDTO.class) Customer newCustomer) {
		Customer result = customerServ.add(newCustomer);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CustomerDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Thêm khách hàng không thành công"));
	}

	/**
	 * Update customer.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param updateCustomer the update customer
	 * @return the response entity
	 */
	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@DTO(CustomerUpdateDTO.class) Customer updateCustomer) {
		Customer result = customerServ.update(updateCustomer);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CustomerDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Cập nhật khách hàng không thành công"));
	}

	/*
	 * 
	 */

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
	 * Gets the map customer result.
	 *
	 * @param pageResult the page result
	 * @return the map customer result
	 */
	private Map<String, Object> getMapCustomerResult(Page<Customer> pageResult) {
		Map<String, Object> map = new HashMap<>();
		List<CustomerDTO> list = modelMapper.map(pageResult.getContent(), new TypeToken<List<CustomerDTO>>() {
		}.getType());
		map.put("customers", list);
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());

		return map;
	}

}
