package quanaotreem.controller.api;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import quanaotreem.dto.CustomerDTO;
import quanaotreem.dto.create.CustomerCreateDTO;
import quanaotreem.dto.mapper.DTO;
import quanaotreem.dto.update.CustomerUpdateDTO;
import quanaotreem.entity.Account;
import quanaotreem.entity.Customer;
import quanaotreem.entity.Role;
import quanaotreem.handler.MyException;
import quanaotreem.response.MessageResponse;
import quanaotreem.service.AccountServ;
import quanaotreem.service.CustomerServ;
import quanaotreem.service.RoleServ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "${cross.origin}", maxAge = 3600)
@RequestMapping("/api")
public class CustomerApi {

	@Autowired
	private CustomerServ customerServ;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AccountServ accountServ;

	@Autowired
	private RoleServ roleServ;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Find all.
	 *
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @return the response entity
	 * @throws MyException the my excetion
	 */
	@GetMapping("/customers")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort)
			throws MyException {
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

		return ResponseEntity.badRequest().body(new MessageResponse("Kh??ng t??m th???y kh??ch h??ng n??o"));
	}

	/**
	 * Add the customer.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param newCustomer the new customer
	 * @return the response entity
	 * @throws MyException
	 */
	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@DTO(CustomerCreateDTO.class) Customer newCustomer) throws MyException {
		Account newAccount = newCustomer.getAccount();
		if (accountServ.existsByUsername(newAccount.getUsername()))
			throw new MyException("Username ???? t???n t???i trong h??? th???ng. Vui l??ng ch???n Username kh??c");
		if (customerServ.existsByEmail(newCustomer.getEmail()))
			throw new MyException("Email ???? t???n t???i trong h??? th???ng. Vui l??ng ch???n Email kh??c");

		Set<Role> roles = newAccount.getRoles().stream().map(item -> roleServ.findByName(item.getName()))
				.collect(Collectors.toSet());
		newAccount.setRoles(roles);
		newAccount.setCustomer(newCustomer);
		newAccount.setPassword(encoder.encode(newAccount.getPassword()));

		Customer result = customerServ.add(newCustomer);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CustomerDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Th??m kh??ch h??ng kh??ng th??nh c??ng"));
	}

	/**
	 * Update customer.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param updateCustomer the update customer
	 * @return the response entity
	 * @throws MyException
	 */
	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@DTO(CustomerUpdateDTO.class) Customer updateCustomer) throws MyException {
		Customer existsCustomer = customerServ.findByEmail(updateCustomer.getEmail());
		if (existsCustomer != null && !existsCustomer.equals(updateCustomer))
			throw new MyException("Email ???? t???n t???i trong h??? th???ng. Vui l??ng ch???n Email kh??c");

		Customer result = customerServ.update(updateCustomer);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CustomerDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("C???p nh???t kh??ch h??ng kh??ng th??nh c??ng"));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
		try {
			customerServ.delete(id);
			return ResponseEntity.ok(new MessageResponse("X??a th??nh c??ng kh??ch h??ng"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(
					"X??a kh??ch h??ng kh??ng th??nh c??ng. Ch??? x??a ???????c khi kh??ch h??ng n??y ch??a l???p h??a ????n n??o"));
		}
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
	 * @throws MyException the my excetion
	 */
	private List<Order> getListSortOrder(String[] sort) throws MyException {
		List<Order> orders = new ArrayList<>();
		try {
			for (String s : sort) {
				if (s.contains("-")) {
					for (String sortOrder : sort) {
						String[] subSort = sortOrder.split("-");
						orders.add(new Order(getSortDirection(subSort[1]), subSort[0]));
					}
				}
			}
		} catch (Exception e) {
			throw new MyException("L???i: Vui l??ng ki???m tra l???i tham s??? sort");
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
