package maikiencuong.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import maikiencuong.entity.InvenColorSize;
import maikiencuong.entity.Product;
import maikiencuong.service.ProductServ;
import maikiencuong.service.SubProductServ;
import maikiencuong.views.Views;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProductContr {

	@Autowired
	private ProductServ productServ;

	@Autowired
	private SubProductServ subProductServ;

	/**
	 * <p>
	 * Lay ra tat ca san pham va phan trang, co the ket hop tat ca cac dieu kien voi
	 * nhau
	 * </p>
	 * 
	 * @param size  ?size=1 default:10
	 * @param page  ?page=1 default: 0
	 * @param query ?q=ao required=false
	 * @param sort  ?sort=id,desc, ?sort=id,desc&sort=name,asc default: price,desc
	 * @return {@code Map<String, Object>} <br>
	 *         products: danh sach san pham cua trang hien tai </br>
	 *         currentPage: trang hien tai </br>
	 *         totalItems: tong so san pham tim duoc </br>
	 *         totalPages: tong so trang </br>
	 */
	@GetMapping("/products")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort) {

		try {
			List<Order> orders = getListSortOrder(sort);
			Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
			Page<Product> pageResult = productServ.findAll(pageable);
			Map<String, Object> map = getMapProductResult(pageResult);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/product/search")
	public ResponseEntity<?> search(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(required = false, value = "q") String query,
			@RequestParam(defaultValue = "name-asc") String[] sort) {

		try {
			List<Order> orders = getListSortOrder(sort);
			Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
			Page<Product> pageResult;
			if (query == null) {
				return ResponseEntity.ok(new HashMap<String, Object>());
			} else {
				pageResult = productServ.findAllByNameLikeOrCategoryLike("%" + query + "%", "%" + query + "%",
						pageable);
				Map<String, Object> map = getMapProductResult(pageResult);
				return ResponseEntity.ok(map);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/product/category")
	public ResponseEntity<?> findByCategory(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(required = false, value = "q") String query,
			@RequestParam(defaultValue = "name-asc") String[] sort) {

		try {
			List<Order> orders = getListSortOrder(sort);
			Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
			Page<Product> pageResult;
			if (query == null) {
				return ResponseEntity.ok(new HashMap<String, Object>());
			} else {
				pageResult = productServ.findAllByCategoryLike("%" + query + "%", pageable);
				Map<String, Object> map = getMapProductResult(pageResult);
				return ResponseEntity.ok(map);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * tim san pham theo id
	 * 
	 * @param id
	 * @return Product
	 */
	@RequestMapping("/product/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Product product = productServ.findById(id);
		if (product != null)
			return ResponseEntity.ok(product);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Lay ra tat ca cac size cua mot san pham
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/product/sizes/{id}")
	public ResponseEntity<?> listSizeById(@PathVariable("id") Long id) {
		Set<InvenColorSize> list = subProductServ.sizeById(id);
		if (!list.isEmpty())
			return ResponseEntity.ok(list);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Lay ra tat ca mau cua mot san pham
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/product/colors/{id}")
	public ResponseEntity<?> listColorById(@PathVariable("id") Long id) {
		Set<InvenColorSize> list = subProductServ.colorById(id);
		if (!list.isEmpty())
			return ResponseEntity.ok(list);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Lay ra tat ca size cua mot san pham theo id va mau
	 * 
	 * @param id
	 * @param color
	 * @return
	 */
	@RequestMapping("/product/size-and-inventory")
	@JsonView(Views.Level2.class)
	public ResponseEntity<?> listSizeByIdAndColor(@RequestParam("id") Long id, @RequestParam("color") String color) {
		Set<InvenColorSize> list = subProductServ.inventoryAndSizeByIdAndColor(id, color);
		if (!list.isEmpty())
			return ResponseEntity.ok(list);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Lay ra danh sach san pham theo marker
	 * 
	 * @param marker HOT, DIS, DEF
	 * @return {@code List<Product>}
	 */
	@RequestMapping("/product/marker")
	public ResponseEntity<?> listProductByMarker(@RequestParam(defaultValue = "8") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort,
			@RequestParam(defaultValue = "HOT", value = "marker") String[] markers) {
		try {
			List<Order> orders = getListSortOrder(sort);
			Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
			Page<Product> pageResult = productServ.findAllByMarkerIn(markers, pageable);
			Map<String, Object> map = getMapProductResult(pageResult);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Lay ra danh sach san pham giam gia hoac views cao
	 * 
	 * @return {@code List<Product>}
	 */

	/**
	 * Lay ra top 10 san pham xem nhieu nhat
	 * 
	 * @return {@code List<Product>}
	 */

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

	private Map<String, Object> getMapProductResult(Page<Product> pageResult) {
		Map<String, Object> map = new HashMap<>();
		map.put("products", pageResult.getContent());
		map.put("currentPage", pageResult.getNumber());
		map.put("totalItems", pageResult.getTotalElements());
		map.put("totalPages", pageResult.getTotalPages());
		return map;
	}

}
