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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import maikiencuong.entity.User;
import maikiencuong.model.request.UserModel;
import maikiencuong.service.UserServ;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {

//	private static final String ERROR_MESSAGE = "Lỗi: Không tìm thấy Role";

	@Autowired
	private UserServ userServ;

//	@Autowired
//	private AccountServ accountServ;
//
//	@Autowired
//	private PasswordEncoder encoder;
//
//	@Autowired
//	private RoleServ roleServ;

	@GetMapping("/users")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort) {

		try {
			List<Order> orders = getListSortOrder(sort);
			Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
			Page<User> pageResult = userServ.findAll(pageable);
			Map<String, Object> map = getMapProductResult(pageResult);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/user/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		User user = userServ.findById(id);
		if (user != null)
			return ResponseEntity.ok(user);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/user")
	public ResponseEntity<?> addCustomer(@RequestBody UserModel userModel) {
		User user = getFromUserModel(userModel);
		User result = userServ.add(user);
		if (result != null)
			return ResponseEntity.ok(result);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/user")
	public ResponseEntity<?> updateCustomer(@RequestBody UserModel userModel) {
		User user = getFromUserModel(userModel);
		User result = userServ.update(user);
		if (result != null)
			return ResponseEntity.ok(result);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
		userServ.delete(id);
		return ResponseEntity.ok("Xoa thanh cong User");
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

	private Map<String, Object> getMapProductResult(Page<User> pageResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("users", pageResult.getContent());
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());
		return map;
	}

	private User getFromUserModel(UserModel userModel) {
//		AccountModel accountModel = userModel.getAccount();
//		Account account = null;
//		if (accountModel != null) {
//			account = Account.builder().username(accountModel.getUsername())
//					.password(encoder.encode(accountModel.getPassword())).build();
//
//			Set<String> strRoles = accountModel.getRole();
//			Set<Role> roles = new HashSet<>();
//
//			if (strRoles == null) {
//				Role userRole = roleServ.findByName(EnumRole.ROLE_USER)
//						.orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
//				roles.add(userRole);
//			} else {
//				strRoles.forEach(role -> {
//					switch (role) {
//					case "admin":
//						Role adminRole = roleServ.findByName(EnumRole.ROLE_ADMIN)
//								.orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
//						roles.add(adminRole);
//
//						break;
//					case "manager":
//						Role modRole = roleServ.findByName(EnumRole.ROLE_MANAGER)
//								.orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
//						roles.add(modRole);
//
//						break;
//					default:
//						Role userRole = roleServ.findByName(EnumRole.ROLE_USER)
//								.orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
//						roles.add(userRole);
//					}
//				});
//			}
//			account.setRoles(roles);
//		}
//		if (userModel.getId() != null) {
//			User user = userServ.findById(userModel.getId());
//			account = user.getAccount();
//		}
		return User.builder().id(userModel.getId()).address(userModel.getAddress()).birthDate(userModel.getBirthDate())
				.email(userModel.getEmail()).identityCard(userModel.getIdentityCard()).name(userModel.getName())
				.nativeCountry(userModel.getNativeCountry()).phone(userModel.getPhone()).build();
	}

}
