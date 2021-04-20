package maikiencuong.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.ColorDTO;
import maikiencuong.dto.ProductDTO;
import maikiencuong.dto.SizeDTO;
import maikiencuong.dto.SizeInventoryDTO;
import maikiencuong.entity.Product;
import maikiencuong.entity.SubProduct;
import maikiencuong.handler.MyExcetion;
import maikiencuong.service.ProductServ;
import maikiencuong.service.SubProductServ;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProductApi {

	@Autowired
	private ProductServ productServ;

	@Autowired
	private SubProductServ subProductServ;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/products")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort)
			throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Product> pageResult = productServ.findAll(pageable);
		Map<String, Object> map = getMapProductResult(pageResult);
		return ResponseEntity.ok(map);
	}

	@RequestMapping("/product/search")
	public ResponseEntity<?> search(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(required = false, value = "q") String query,
			@RequestParam(defaultValue = "name-asc") String[] sort) throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Product> pageResult;
		if (query == null) {
			pageResult = productServ.findAll(pageable);
		} else {
			pageResult = productServ.findAllByNameLikeOrCategory_NameLike(query, query, pageable);
		}
		return ResponseEntity.ok(getMapProductResult(pageResult));
	}

	@RequestMapping("/product/category")
	public ResponseEntity<?> findByCategory(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(required = false, value = "q") String query,
			@RequestParam(defaultValue = "name-asc") String[] sort) throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Product> pageResult;
		if (query == null) {
			pageResult = productServ.findAll(pageable);
		} else {
			pageResult = productServ.findAllByCategory_NameLike(query, pageable);
		}
		return ResponseEntity.ok(getMapProductResult(pageResult));
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Product result = productServ.findById(id);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, ProductDTO.class));
		return ResponseEntity.ok("Không tìm thấy sản phẩm");
	}

	@GetMapping("/product/sizes/{id}")
	public ResponseEntity<?> listSizeById(@PathVariable("id") Long id) {
		List<SubProduct> list = subProductServ.findAllByProduct_Id(id);
		if (!list.isEmpty()) {
			Set<SizeDTO> set = modelMapper.map(list, new TypeToken<Set<SizeDTO>>() {
			}.getType());
			return ResponseEntity.ok(set);
		}
		return ResponseEntity.ok("Danh sách trống");
	}

	@GetMapping("/product/colors/{id}")
	public ResponseEntity<?> listColorById(@PathVariable("id") Long id) {
		List<SubProduct> list = subProductServ.findAllByProduct_Id(id);
		if (!list.isEmpty()) {
			Set<ColorDTO> set = modelMapper.map(list, new TypeToken<Set<ColorDTO>>() {
			}.getType());
			return ResponseEntity.ok(set);
		}
		return ResponseEntity.ok("Danh sách trống");
	}

	@GetMapping("/product/size-and-inventory")
	public ResponseEntity<?> listSizeByIdAndColor(@RequestParam("id") Long id, @RequestParam("color") String color) {
		List<SubProduct> list = subProductServ.findAllByProduct_IdAndColor(id, color);
		if (!list.isEmpty()) {
			Set<SizeInventoryDTO> set = modelMapper.map(list, new TypeToken<Set<SizeInventoryDTO>>() {
			}.getType());
			return ResponseEntity.ok(set);
		}
		return ResponseEntity.ok("Danh sách trống");
	}

	@GetMapping("/product/marker")
	public ResponseEntity<?> listProductByMarker(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort,
			@RequestParam(defaultValue = "HOT", value = "marker") String[] markers) throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Product> pageResult = productServ.findAllByMarkerIn(markers, pageable);
		return ResponseEntity.ok(getMapProductResult(pageResult));
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

	private Map<String, Object> getMapProductResult(Page<Product> pageResult) {
		Map<String, Object> map = new HashMap<>();
		List<ProductDTO> list = modelMapper.map(pageResult.getContent(), new TypeToken<List<ProductDTO>>() {
		}.getType());
		map.put("products", list);
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());
		return map;
	}

}
