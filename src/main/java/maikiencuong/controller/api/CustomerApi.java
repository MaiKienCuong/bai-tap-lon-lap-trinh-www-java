package maikiencuong.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.DTO;
import maikiencuong.dto.MessageResponse;
import maikiencuong.entity.Account;
import maikiencuong.entity.Customer;
import maikiencuong.entity.Role;
import maikiencuong.service.AccountServ;
import maikiencuong.service.CustomerServ;
import maikiencuong.service.RoleServ;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CustomerApi {

	@Autowired
	private CustomerServ customerServ;

	@Autowired
	private AccountServ accountServ;

	@Autowired
	RoleServ roleServ;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/customers")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort) {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Customer> pageResult = customerServ.findAll(pageable);
		Map<String, Object> map = getMapProductResult(pageResult);
		return ResponseEntity.ok(map);
	}

	@RequestMapping("/customer/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Customer result = customerServ.findById(id);
		if (result != null)
			return ResponseEntity.ok(result);
		return ResponseEntity.ok(new MessageResponse("Không tìm thấy khách hàng"));
	}

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@DTO(maikiencuong.dto.CustomerCreateDTO.class) Customer customer) {
		Account account = customer.getAccount();
		if (accountServ.existsByUsername(account.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác"));
		}
		if (customerServ.existsByEmail(customer.getEmail())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác"));
		}
		Set<Role> roles = account.getRoles().stream().map(item -> roleServ.findByName(item.getName()))
				.collect(Collectors.toSet());
		account.setPassword(encoder.encode(account.getPassword()));
		account.setRoles(roles);
		Customer result = customerServ.add(customer);
		if (result != null)
			return ResponseEntity.ok(result);
		return ResponseEntity.badRequest().body(new MessageResponse("Thêm khách hàng không thành công"));
	}

	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@DTO(maikiencuong.dto.CustomerUpdateDTO.class) Customer customer) {
		Account account = customer.getAccount();
		account.setPassword(encoder.encode(account.getPassword()));
		Customer result = customerServ.update(customer);
		if (result != null)
			return ResponseEntity.ok(result);
		return ResponseEntity.badRequest().body(new MessageResponse("Cập nhật khách hàng không thành công"));
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) throws Exception {
		try {
			customerServ.delete(id);
			return ResponseEntity.ok(new MessageResponse("Xóa thành công khách hàng"));
		} catch (Exception e) {
			throw new Exception("Xóa khách hàng không thành công");
		}
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
		if (sort.length > 1) {
			if (sort[0].contains("-")) {
				// neu sort tren nhieu hon 1 field
				// ?sort=id-desc&sort=title-asc
				for (String sortOrder : sort) {
					String[] subSort = sortOrder.split("-");
					orders.add(new Order(getSortDirection(subSort[1]), subSort[0]));
				}
			} else {
				// neu chi sort tren 1 field
				// ?sort=id-desc
				orders.add(new Order(getSortDirection(sort[1]), sort[0]));
			}
		}
		return orders;
	}

	private Map<String, Object> getMapProductResult(Page<Customer> pageResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("customers", pageResult.getContent());
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());
		return map;
	}

	/*
	 * private Customer getFromCustomerModel(CustomerCreateDTO customerModel) {
	 * Customer customer = new Customer(); AccountCreateDTO accountModel =
	 * customerModel.getAccount(); if (customerModel.getId() != null) { customer =
	 * customerServ.findById(customerModel.getId()); } if (customer != null) {
	 * Account account = customer.getAccount(); if (account == null) { account = new
	 * Account(); Set<Role> roles = new HashSet<>();
	 * roles.add(roleServ.findByName(EnumRole.ROLE_CUSTOMER));
	 * account.setRoles(roles); account.setUsername(accountModel.getUsername()); }
	 * account.setEnable(accountModel.isEnable());
	 * account.setPassword(encoder.encode(accountModel.getPassword()));
	 * customer.setEmail(customerModel.getEmail());
	 * customer.setName(customerModel.getName());
	 * customer.setPhone(customerModel.getPhone()); customer.setAccount(account);
	 * customer.setAddress(customerModel.getAddress()); } return customer; }
	 */

}
