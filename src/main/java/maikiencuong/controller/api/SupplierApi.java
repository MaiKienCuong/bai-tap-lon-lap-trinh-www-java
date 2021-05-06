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

import maikiencuong.dto.SupplierDTO;
import maikiencuong.dto.create.SupplierCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.dto.update.SupplierUpdateDTO;
import maikiencuong.entity.Supplier;
import maikiencuong.handler.MyExcetion;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.SupplierServ;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SupplierApi {

	@Autowired
	private SupplierServ supplierServ;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/suppliers")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort)
			throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Supplier> pageResult = supplierServ.findAll(pageable);

		return ResponseEntity.ok(getMapSupplierResult(pageResult));
	}

	@GetMapping("/supplier/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Supplier result = supplierServ.findById(id);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, SupplierDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Không tìm thấy"));
	}

	@PostMapping("/supplier")
	public ResponseEntity<?> addSupplier(@DTO(SupplierCreateDTO.class) Supplier supplier) {
		Supplier result = supplierServ.add(supplier);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, SupplierDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Thêm không thành công"));
	}

	@PutMapping("/supplier")
	public ResponseEntity<?> updateSupplier(@DTO(SupplierUpdateDTO.class) Supplier supplier) {
		Supplier result = supplierServ.update(supplier);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, SupplierDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Cập nhật không thành công"));
	}

	@DeleteMapping("/supplier/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable("id") Long id) {
		try {
			supplierServ.delete(id);
			return ResponseEntity.ok(new MessageResponse("Xóa thành công"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Xóa không thành công"));
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

	private Map<String, Object> getMapSupplierResult(Page<Supplier> pageResult) {
		Map<String, Object> map = new HashMap<>();
		List<SupplierDTO> list = modelMapper.map(pageResult.getContent(), new TypeToken<List<SupplierDTO>>() {
		}.getType());
		map.put("suppliers", list);
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());

		return map;
	}

}