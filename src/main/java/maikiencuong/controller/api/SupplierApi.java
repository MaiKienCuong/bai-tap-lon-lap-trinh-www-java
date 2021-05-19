package maikiencuong.controller.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.SupplierDTO;
import maikiencuong.dto.create.SupplierCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.dto.update.SupplierUpdateDTO;
import maikiencuong.entity.Supplier;
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

	/**
	 * Find all.
	 *
	 * @return the response entity
	 */
	@GetMapping("/suppliers")
	public ResponseEntity<?> findAll() {
		List<Supplier> suppliers = supplierServ.findAll();
		if (!suppliers.isEmpty()) {
			List<SupplierDTO> list = modelMapper.map(suppliers, new TypeToken<List<SupplierDTO>>() {
			}.getType());
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Danh sách trống"));
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/supplier/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Supplier result = supplierServ.findById(id);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, SupplierDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Không tìm thấy"));
	}

	/**
	 * Add the supplier.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param newSupplier the new supplier
	 * @return the response entity
	 */
	@PostMapping("/supplier")
	public ResponseEntity<?> addSupplier(@DTO(SupplierCreateDTO.class) Supplier newSupplier) {
		Supplier result = supplierServ.add(newSupplier);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, SupplierDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Thêm nhà cung cấp không thành công"));
	}

	/**
	 * Update supplier.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param updateSupplier the update supplier
	 * @return the response entity
	 */
	@PutMapping("/supplier")
	public ResponseEntity<?> updateSupplier(@DTO(SupplierUpdateDTO.class) Supplier updateSupplier) {
		Supplier result = supplierServ.update(updateSupplier);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, SupplierDTO.class));

		return ResponseEntity.badRequest().body(new MessageResponse("Cập nhật nhà cung cấp không thành công"));
	}

	/**
	 * Delete supplier.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/supplier/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable("id") Long id) {
		try {
			supplierServ.delete(id);
			return ResponseEntity.ok(new MessageResponse("Xóa nhà cung cấp thành công"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Xóa nhà cung cấp không thành công"));
		}
	}

}
