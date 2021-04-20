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

import maikiencuong.dto.CategoryDTO;
import maikiencuong.dto.create.CategoryCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.dto.update.CategoryUpdateDTO;
import maikiencuong.entity.Category;
import maikiencuong.handler.MyExcetion;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.CategoryServ;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryApi {

	@Autowired
	private CategoryServ categoryServ;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/categories")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort)
			throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Category> pageResult = categoryServ.findAll(pageable);

		return ResponseEntity.ok(getMapCategoryResult(pageResult));
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Category result = categoryServ.findById(id);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CategoryDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Không tìm thấy loại sản phẩm nào"));
	}

	@PostMapping("/category")
	public ResponseEntity<?> addCategory(@DTO(CategoryCreateDTO.class) Category category) {
		Category result = categoryServ.add(category);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CategoryDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Thêm không thành công"));
	}

	@PutMapping("/category")
	public ResponseEntity<?> updateCategory(@DTO(CategoryUpdateDTO.class) Category category) {
		Category result = categoryServ.update(category);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CategoryDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Cập nhật không thành công"));
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
		try {
			categoryServ.delete(id);
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

	private Map<String, Object> getMapCategoryResult(Page<Category> pageResult) {
		Map<String, Object> map = new HashMap<>();
		List<CategoryDTO> list = modelMapper.map(pageResult.getContent(), new TypeToken<List<CategoryDTO>>() {
		}.getType());
		map.put("categories", list);
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());

		return map;
	}

}
