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
import org.springframework.transaction.annotation.Transactional;
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
import quanaotreem.dto.ColorDTO;
import quanaotreem.dto.ProductDTO;
import quanaotreem.dto.SizeDTO;
import quanaotreem.dto.SizeInventoryDTO;
import quanaotreem.dto.create.ProductCreateDTO;
import quanaotreem.dto.mapper.DTO;
import quanaotreem.dto.update.ProductUpdateDTO;
import quanaotreem.entity.Category;
import quanaotreem.entity.Image;
import quanaotreem.entity.Product;
import quanaotreem.entity.SubProduct;
import quanaotreem.entity.Supplier;
import quanaotreem.handler.MyException;
import quanaotreem.response.MessageResponse;
import quanaotreem.service.CategoryServ;
import quanaotreem.service.ImageServ;
import quanaotreem.service.ProductServ;
import quanaotreem.service.SubProductServ;
import quanaotreem.service.SupplierServ;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@RestController
@CrossOrigin(origins = "${cross.origin}", maxAge = 3600)
@RequestMapping("/api")
public class ProductApi {

    @Autowired
    private ImageServ imageServ;

    @Autowired
    private ProductServ productServ;

    @Autowired
    private SupplierServ supplierServ;

    @Autowired
    private CategoryServ categoryServ;

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
     * @throws MyException the my excetion
     */
    @GetMapping("/products")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "12") int size,
                                     @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort)
            throws MyException {
        List<Order> orders = getListSortOrder(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        Page<Product> pageResult = productServ.findAll(pageable);

        return ResponseEntity.ok(getMapProductResult(pageResult));
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

        return ResponseEntity.badRequest().body(new MessageResponse("Kh??ng t??m th???y s???n ph???m"));
    }

    /**
     * Add the product.
     *
     * @param newProduct the new product
     * @return the response entity
     * @throws MyException
     * @DTO danh dau body cua request gui len la dang DTO, nhung argument co
     * annotation DTO se duoc modelMaper tu dong chuyen qua entity
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/product")
    public ResponseEntity<?> addProduct(@DTO(ProductCreateDTO.class) Product newProduct) throws MyException {
        Category category = categoryServ.findById(newProduct.getCategory().getId());
        Supplier supplier = supplierServ.findById(newProduct.getSupplier().getId());

        if (category == null)
            throw new MyException("Kh??ng t??m th???y th??ng tin lo???i s???n ph???m. Vui l??ng th??m lo???i s???n ph???m n??y tr?????c");
        if (supplier == null)
            throw new MyException("Kh??ng t??m th???y th??ng tin nh?? cung c???p. Vui l??ng th??m nh?? cung c???p n??y tr?????c");
        if (newProduct.getSubProducts().isEmpty())
            throw new MyException("Ch??a c?? danh s??ch s???n ph???m con");

        if (newProduct.getDiscount() > 0)
            newProduct.setMarker("DIS");
        else
            newProduct.setMarker("DEF");

        newProduct.setCategory(category);
        newProduct.setSupplier(supplier);
        newProduct.getSubProducts().forEach(subProduct -> subProduct.setProduct(newProduct));
        newProduct.getImagesUrl().forEach(image -> image.setProduct(newProduct));

        Product result = productServ.add(newProduct);
        if (result != null)
            return ResponseEntity.ok(modelMapper.map(result, ProductDTO.class));
        return ResponseEntity.badRequest().body(new MessageResponse("Th??m s???n ph???m kh??ng th??nh c??ng"));

    }

    /**
     * Update product.
     *
     * @param productUpdateDTO the product update DTO
     * @return the response entity
     * @throws MyException
     * @Valid danh dau de cho spring kiem tra tinh hop le cua du lieu
     * @RequestBody danh dau de spring tu dong map body cua request sang DTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/product")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductUpdateDTO productUpdateDTO) throws MyException {
        if (productUpdateDTO.getSubProducts().isEmpty())
            throw new MyException("Danh s??ch s???n ph???m con tr???ng");

        List<Image> images = imageServ.findAllByProduct_Id(productUpdateDTO.getId());
        images.forEach(image -> {
            image.setProduct(null);
            imageServ.deleteById(image.getId());
        });

        Product product = productServ.findById(productUpdateDTO.getId());
        if (product != null) {
            int views = product.getViews();

            for (Image image : productUpdateDTO.getImagesUrl()) {
                image.setProduct(product);
            }
            product = modelMapper.map(productUpdateDTO, Product.class);
            if (product.getDiscount() > 0)
                product.setMarker("DIS");
            else
                product.setMarker("DEF");
            product.setViews(views);

            Product result = productServ.update(product);
            if (result != null)
                return ResponseEntity.ok(modelMapper.map(result, ProductDTO.class));
            return ResponseEntity.badRequest().body(new MessageResponse("C???p nh???t s???n ph???m kh??ng th??nh c??ng"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Kh??ng t??m th???y s???n ph???m n??y ????? c???p nh???t"));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        try {
            productServ.delete(id);
            return ResponseEntity.ok(new MessageResponse("X??a th??nh c??ng s???n ph???m"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "X??a s???n ph???m kh??ng th??nh c??ng. Ch??? x??a ???????c khi s???n ph???m n??y ch??a ???????c l???p h??a ????n"));
        }
    }

    /**
     * List size by id.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/product/sizes/{productId}")
    public ResponseEntity<?> listSizeById(@PathVariable("productId") Long id) {
        List<SubProduct> list = subProductServ.findAllByProduct_Id(id);
        if (!list.isEmpty()) {
            Set<SizeDTO> sizes = modelMapper.map(list, new TypeToken<TreeSet<SizeDTO>>() {
            }.getType());
            return ResponseEntity.ok(sizes);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Danh s??ch tr???ng"));
    }

    /**
     * List color by id.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/product/colors/{productId}")
    public ResponseEntity<?> listColorById(@PathVariable("productId") Long id) {
        List<SubProduct> list = subProductServ.findAllByProduct_Id(id);
        if (!list.isEmpty()) {
            Set<ColorDTO> colors = modelMapper.map(list, new TypeToken<TreeSet<ColorDTO>>() {
            }.getType());
            return ResponseEntity.ok(colors);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Danh s??ch tr???ng"));
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
            Set<SizeInventoryDTO> sizeInventories = modelMapper.map(list, new TypeToken<TreeSet<SizeInventoryDTO>>() {
            }.getType());
            return ResponseEntity.ok(sizeInventories);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Danh s??ch tr???ng"));
    }

    /**
     * Find by marker.
     *
     * @param size    the size
     * @param page    the page
     * @param sort    the sort
     * @param markers the markers
     * @return the response entity
     * @throws MyException the my excetion
     */
    @Transactional
    @GetMapping("/product/marker")
    public ResponseEntity<?> findByMarker(@RequestParam(defaultValue = "12") int size,
                                          @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name-asc") String[] sort,
                                          @RequestParam(defaultValue = "HOT", value = "marker") String[] markers) throws MyException {
        List<Product> listHot = productServ.findAllByMarkerIn(new String[]{"HOT"});
        listHot.forEach(prod -> {
            if (prod.getDiscount() > 0)
                prod.setMarker("DIS");
            else
                prod.setMarker("DEF");
//			productServ.update(prod);
        });
        List<Product> top8view = productServ.findTop8ByOrderByViewsDesc();
        top8view.forEach(product -> {
            product.setMarker("HOT");
//			productServ.update(product);
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
     * @throws MyException the my excetion
     */
    @RequestMapping("/product/search")
    public ResponseEntity<?> findByProductNameOrCateGoryName(@RequestParam(defaultValue = "12") int size,
                                                             @RequestParam(defaultValue = "0") int page, @RequestParam(required = false, value = "q") String query,
                                                             @RequestParam(defaultValue = "name-asc") String[] sort) throws MyException {
        List<Order> orders = getListSortOrder(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        Page<Product> pageResult;
        if (query == null) {
            pageResult = productServ.findAll(pageable);
        } else {
            query = query.replaceAll("[\\s]+", " ").trim();
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
     * @throws MyException the my excetion
     */
    @RequestMapping("/product/category")
    public ResponseEntity<?> findByCategoryName(@RequestParam(defaultValue = "12") int size,
                                                @RequestParam(defaultValue = "0") int page, @RequestParam(required = false, value = "q") String query,
                                                @RequestParam(defaultValue = "name-asc") String[] sort) throws MyException {
        List<Order> orders = getListSortOrder(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        Page<Product> pageResult;
        if (query == null) {
            pageResult = productServ.findAll(pageable);
        } else {
            query = query.replaceAll("[\\s]+", " ").trim();
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
