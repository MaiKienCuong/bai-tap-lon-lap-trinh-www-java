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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.entity.Role;
import maikiencuong.handler.MyExcetion;
import maikiencuong.service.RoleServ;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoleApi {

	@Autowired
	private RoleServ roleServ;

	@RequestMapping("/roles")
	public ResponseEntity<?> getAllRole(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort)
			throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Role> pageResult = roleServ.findAll(pageable);
		Map<String, Object> map = getMapRoleResult(pageResult);
		return ResponseEntity.ok(map);
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

	private Map<String, Object> getMapRoleResult(Page<Role> pageResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("roles", pageResult.getContent());
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());
		return map;
	}
}
