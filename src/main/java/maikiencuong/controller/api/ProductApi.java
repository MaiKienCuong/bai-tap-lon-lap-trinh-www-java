package maikiencuong.controller.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.ColorDTO;
import maikiencuong.dto.ProductDTO;
import maikiencuong.dto.SizeDTO;
import maikiencuong.dto.SizeInventoryDTO;
import maikiencuong.dto.create.ProductCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.dto.update.ProductUpdateDTO;
import maikiencuong.entity.Product;
import maikiencuong.entity.SubProduct;
import maikiencuong.handler.MyExcetion;
import maikiencuong.payload.response.MessageResponse;
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

	/**
	 * Find all.
	 *
	 * @param size the size
	 * @param page the page
	 * @param sort the sort
	 * @return the response entity
	 * @throws MyExcetion the my excetion
	 */
	@GetMapping("/products")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "12") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort)
			throws MyExcetion {
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Product> pageResult = productServ.findAll(pageable);

		return ResponseEntity.ok(getMapProductResult(pageResult));
	}

	/**
	 * Add the product.
	 * 
	 * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
	 *      annotation DTO se duoc modelMaper tu dong chuyen qua entity
	 *
	 * @param newProduct the new product
	 * @return the response entity
	 */
	@PostMapping(value = "/product")
	public ResponseEntity<?> addProduct(@DTO(ProductCreateDTO.class) Product newProduct) {
		Product result = productServ.add(newProduct);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, ProductDTO.class));
		return ResponseEntity.badRequest().body(new MessageResponse("Thêm sản phẩm không thành công"));

	}

	/**
	 * Update product.
	 * 
	 * @Valid danh dau de cho spring kiem tra tinh hop le cua du lieu
	 * @RequestBody danh dau de spring tu dong map body cua request sang DTO
	 *
	 * @param productUpdateDTO the product update DTO
	 * @return the response entity
	 */
	@PutMapping(value = "/product")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
		Product product = productServ.findById(productUpdateDTO.getId());
		Integer views = product.getViews();
		LocalDateTime createdAt = product.getCreatedAt();

		product = modelMapper.map(productUpdateDTO, Product.class);
		product.setViews(views);
		product.setCreatedAt(createdAt);
		Product result = productServ.update(product);
		if (result != null)
			return ResponseEntity.ok(modelMapper.map(result, ProductDTO.class));
		return ResponseEntity.badRequest().body(new MessageResponse("Cập nhật sản phẩm không thành công"));

	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Product result = productServ.findById(id);
		if (result != null) {
			result.setViews(result.getViews() + 1);
			productServ.update(result);
			return ResponseEntity.ok(modelMapper.map(result, ProductDTO.class));
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Không tìm thấy sản phẩm"));
	}

	/**
	 * List size by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/product/sizes/{id}")
	public ResponseEntity<?> listSizeById(@PathVariable("id") Long id) {
		List<SubProduct> list = subProductServ.findAllByProduct_Id(id);
		if (!list.isEmpty()) {
			Set<SizeDTO> set = modelMapper.map(list, new TypeToken<Set<SizeDTO>>() {
			}.getType());
			return ResponseEntity.ok(set);
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Danh sách trống"));
	}

	/**
	 * List color by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/product/colors/{id}")
	public ResponseEntity<?> listColorById(@PathVariable("id") Long id) {
		List<SubProduct> list = subProductServ.findAllByProduct_Id(id);
		if (!list.isEmpty()) {
			Set<ColorDTO> set = modelMapper.map(list, new TypeToken<Set<ColorDTO>>() {
			}.getType());
			return ResponseEntity.ok(set);
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Danh sách trống"));
	}

	/**
	 * List size and inventory by id and color.
	 *
	 * @param id    the id
	 * @param color the color
	 * @return the response entity
	 */
	@GetMapping("/product/size-and-inventory")
	public ResponseEntity<?> listSizeAndInventoryByIdAndColor(@RequestParam("id") Long id,
			@RequestParam("color") String color) {
		List<SubProduct> list = subProductServ.findAllByProduct_IdAndColor(id, color);
		if (!list.isEmpty()) {
			Set<SizeInventoryDTO> set = modelMapper.map(list, new TypeToken<Set<SizeInventoryDTO>>() {
			}.getType());
			return ResponseEntity.ok(set);
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Danh sách trống"));
	}

	/**
	 * Find by marker.
	 *
	 * @param size    the size
	 * @param page    the page
	 * @param sort    the sort
	 * @param markers the markers
	 * @return the response entity
	 * @throws MyExcetion the my excetion
	 */
	@GetMapping("/product/marker")
	public ResponseEntity<?> findByMarker(@RequestParam(defaultValue = "12") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort,
			@RequestParam(defaultValue = "HOT", value = "marker") String[] markers) throws MyExcetion {
		List<Product> list = productServ.findTop10ByOrderByViewsDesc();
		list.forEach(product -> {
			product.setMarker("HOT");
			productServ.update(product);
		});
		List<Order> orders = getListSortOrder(sort);
		Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
		Page<Product> pageResult = productServ.findAllByMarkerIn(markers, pageable);
		return ResponseEntity.ok(getMapProductResult(pageResult));
	}

	/**
	 * Find by product name or cate gory name.
	 *
	 * @param size  the size
	 * @param page  the page
	 * @param query the query
	 * @param sort  the sort
	 * @return the response entity
	 * @throws MyExcetion the my excetion
	 */
	@RequestMapping("/product/search")
	public ResponseEntity<?> findByProductNameOrCateGoryName(@RequestParam(defaultValue = "12") int size,
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

	/**
	 * Find by category name.
	 *
	 * @param size  the size
	 * @param page  the page
	 * @param query the query
	 * @param sort  the sort
	 * @return the response entity
	 * @throws MyExcetion the my excetion
	 */
	@RequestMapping("/product/category")
	public ResponseEntity<?> findByCategoryName(@RequestParam(defaultValue = "12") int size,
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
	 * @throws MyExcetion the my excetion
	 */
	private List<Order> getListSortOrder(String[] sort) throws MyExcetion {
		List<Order> orders = new ArrayList<>();
		try {
			for (int i = 0; i < sort.length; i++) {
				if (sort[i].contains("-")) {
					for (String sortOrder : sort) {
						String[] subSort = sortOrder.split("-");
						orders.add(new Order(getSortDirection(subSort[1]), subSort[0]));
					}
				}
			}
		} catch (Exception e) {
			throw new MyExcetion("Lỗi: Vui lòng kiểm tra lại tham số sort");
		}

		return orders;
	}

	/**
	 * Gets the map product result.
	 *
	 * @param pageResult the page result
	 * @return the map product result
	 */
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
