package maikiencuong.aspects;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import maikiencuong.dto.update.ProductUpdateDTO;
import maikiencuong.entity.Category;
import maikiencuong.entity.Image;
import maikiencuong.entity.Product;
import maikiencuong.entity.Supplier;
import maikiencuong.service.CategoryServ;
import maikiencuong.service.ImageServ;
import maikiencuong.service.SupplierServ;

/**
 * The Class ProductAspect.
 * 
 * @Aspect danh dau day la mot class aspect, dung de pre-process cac phuong thuc
 *         addProduct, updateProduct trong controller
 * 
 */
@Aspect
@Component
public class ProductAspect {

	@Autowired
	private CategoryServ categoryServ;

	@Autowired
	private SupplierServ supplierServ;

	@Autowired
	private ImageServ imageServ;

	/**
	 * Before add product.
	 * 
	 * <p>
	 * Phuong thuc nay se duoc thuc hien truoc khi phuong thuc addProduct trong
	 * controller duoc goi, dung de kiem tra san pham truoc khi them vao database
	 * </p>
	 *
	 * @param joinPoint the join point
	 */
	@Before("execution(* maikiencuong.controller.api.ProductApi.addProduct(..))")
	public void beforeAddProduct(JoinPoint joinPoint) {
		Product newProduct = (Product) joinPoint.getArgs()[0];

		Category category = categoryServ.findById(newProduct.getCategory().getId());
		Supplier supplier = supplierServ.findById(newProduct.getSupplier().getId());
		newProduct.setCategory(category);
		newProduct.setSupplier(supplier);

		newProduct.getSubProducts().forEach(subProduct -> subProduct.setProduct(newProduct));
		newProduct.getImagesUrl().forEach(image -> image.setProduct(newProduct));

	}

	/**
	 * Before update product.
	 * 
	 * <p>
	 * Phuong thuc nay se duoc thuc hien truoc khi phuong thuc updateProduct trong
	 * controller duoc goi
	 * </p>
	 *
	 * @param joinPoint the join point
	 */
	@Before("execution(* maikiencuong.controller.api.ProductApi.updateProduct(..))")
	public void beforeUpdateProduct(JoinPoint joinPoint) {
		ProductUpdateDTO updateProduct = (ProductUpdateDTO) joinPoint.getArgs()[0];

		List<Image> images = imageServ.findAllByProduct_Id(updateProduct.getId());
		images.forEach(image -> imageServ.deleteById(image.getId()));

		Product productEntity = Product.builder().id(updateProduct.getId()).build();
		updateProduct.getImagesUrl().forEach(image -> image.setProduct(productEntity));

	}

}
