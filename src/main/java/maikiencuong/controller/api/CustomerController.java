package maikiencuong.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.entity.Account;
import maikiencuong.entity.Customer;
import maikiencuong.entity.EnumRole;
import maikiencuong.entity.Role;
import maikiencuong.model.request.AccountModel;
import maikiencuong.model.request.CustomerModel;
import maikiencuong.model.response.MessageResponse;
import maikiencuong.service.AccountServ;
import maikiencuong.service.CustomerServ;
import maikiencuong.service.RoleServ;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerServ customerServ;

	@Autowired
	private AccountServ accountServ;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleServ roleServ;

	private static final String ERROR_MESSAGE = "Lỗi: Không tìm thấy Role";

	@GetMapping("/customers")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort) {

		try {
			List<Order> orders = getListSortOrder(sort);
			Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
			Page<Customer> pageResult = customerServ.findAll(pageable);
			Map<String, Object> map = getMapProductResult(pageResult);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/customer/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Customer customer = customerServ.findById(id);
		if (customer != null)
			return ResponseEntity.ok(customer);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerModel customerModel) {
//		AccountModel accountModel = customerModel.getAccount();
//		if (accountModel != null && accountServ.existsByUsername(accountModel.getUsername())) {
//			return ResponseEntity.badRequest()
//					.body(new MessageResponse("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác"));
//		}
		Customer customer = getFromCustomerModel(customerModel);
		Customer result = customerServ.add(customer);
		if (result != null)
			return ResponseEntity.ok(result);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerModel customerModel) {
//		AccountModel accountModel = customerModel.getAccount();
//		if (accountModel != null && accountServ.existsByUsername(accountModel.getUsername())) {
//			return ResponseEntity.badRequest()
//					.body(new MessageResponse("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác"));
//		}
		Customer customer = getFromCustomerModel(customerModel);
		Customer result = customerServ.update(customer);
		if (result != null)
			return ResponseEntity.ok(result);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
		customerServ.delete(id);
		return ResponseEntity.ok("Xoa thanh cong khach hang");
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

	private Map<String, Object> getMapProductResult(Page<Customer> pageResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("customers", pageResult.getContent());
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());
		return map;
	}

	private Customer getFromCustomerModel(CustomerModel customerModel) {
		AccountModel accountModel = customerModel.getAccount();
		Account account = null;
		if (accountModel != null) {
			account = Account.builder().username(accountModel.getUsername())
					.password(encoder.encode(accountModel.getPassword())).build();
			Set<Role> roles = new HashSet<>();

			Role userRole = roleServ.findByName(EnumRole.ROLE_CUSTOMER)
					.orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
			roles.add(userRole);

			account.setRoles(roles);
			return Customer.builder().id(customerModel.getId()).account(account).email(customerModel.getEmail())
					.name(customerModel.getName()).phone(customerModel.getPhone()).build();
		}
		return Customer.builder().id(customerModel.getId()).email(customerModel.getEmail())
				.name(customerModel.getName()).phone(customerModel.getPhone()).build();
	}

}
