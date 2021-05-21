package maikiencuong.controller.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.CategoryDTO;
import maikiencuong.dto.create.CategoryCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.dto.update.CategoryUpdateDTO;
import maikiencuong.entity.Category;
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

	/**
	 * Find all.
	 *
	 * @return the response entity
	 */
	@GetMapping("/categories")
	public ResponseEntity<?> findAll() {
		List<Category> categories = categoryServ.findAll();
		if (!categories.isEmpty()) {
			List<CategoryDTO> list = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {
			}.getType());
			return ResponseEntity.ok(list);
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Danh sách loại sản phẩm trống"));
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/category/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Category result = categoryServ.findById(id);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CategoryDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Không tìm thấy loại sản phẩm nào"));
	}

	/**
	 * Add the category.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param newCategory the new category
	 * @return the response entity
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/category")
	public ResponseEntity<?> addCategory(@DTO(CategoryCreateDTO.class) Category newCategory) {
		Category result = categoryServ.add(newCategory);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CategoryDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Thêm loại sản phẩm không thành công"));
	}

	/**
	 * Update category.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param updateCategory the update category
	 * @return the response entity
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/category")
	public ResponseEntity<?> updateCategory(@DTO(CategoryUpdateDTO.class) Category updateCategory) {
		Category result = categoryServ.update(updateCategory);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, CategoryDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Cập nhật loại sản phẩm không thành công"));
	}

	/**
	 * Delete category.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
		try {
			categoryServ.delete(id);
			return ResponseEntity.ok(new MessageResponse("Xóa thành công loại sản phẩm"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse(
					"Xóa loại sản phẩm không thành công. Chỉ xóa được khi loại sản phẩm này chưa có sản phẩm nào"));
		}
	}

}
