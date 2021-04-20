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
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@GetMapping("/customers")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort)
			throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Customer> pageResult = customerServ.findAll(pageable);
		return ResponseEntity.ok(getMapProductResult(pageResult));
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Customer result = customerServ.findById(id);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CustomerDTO.class));
		return ResponseEntity.ok(new MessageResponse("Không tìm thấy khách hàng"));
	}

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@DTO(CustomerCreateDTO.class) Customer customer) {
		Customer result = customerServ.add(customer);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CustomerDTO.class));
		return ResponseEntity.badRequest().body(new MessageResponse("Thêm khách hàng không thành công"));
	}

	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@DTO(CustomerUpdateDTO.class) Customer customer) {
		Customer result = customerServ.update(customer);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CustomerDTO.class));
		return ResponseEntity.badRequest().body(new MessageResponse("Cập nhật khách hàng không thành công"));
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
		try {
			customerServ.delete(id);
			return ResponseEntity.ok(new MessageResponse("Xóa thành công khách hàng"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Xóa khách hàng không thành công"));
		}
	}

	/*
	 * 
	 */

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

	private Map<String, Object> getMapProductResult(Page<Customer> pageResult) {
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
