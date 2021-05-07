package maikiencuong.aspects;

import java.util.Iterator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import maikiencuong.entity.Category;
import maikiencuong.entity.Image;
import maikiencuong.entity.Product;
import maikiencuong.entity.Supplier;
import maikiencuong.service.CategoryServ;
import maikiencuong.service.ImageServ;
import maikiencuong.service.SupplierServ;

@Aspect
@Component
public class ProductAspect {

	@Autowired
	private CategoryServ categoryServ;

	@Autowired
	private SupplierServ supplierServ;

	@Autowired
	private ImageServ imageServ;

	@Before("execution(* maikiencuong.controller.api.ProductApi.addProduct(..))")
	public void beforeAddProduct(JoinPoint joinPoint) {
		Product product = (Product) joinPoint.getArgs()[0];

		Category category = categoryServ.findById(product.getCategory().getId());
		Supplier supplier = supplierServ.findById(product.getSupplier().getId());
		product.setCategory(category);
		product.setSupplier(supplier);

		product.getSubProducts().forEach(sub -> sub.setProduct(product));
		product.getImagesUrl().forEach(img -> img.setProduct(product));

	}

	@Before("execution(* maikiencuong.controller.api.ProductApi.updateProduct(..))")
	public void beforeUpdateProduct(JoinPoint joinPoint) {
		Product product = (Product) joinPoint.getArgs()[0];
		for (Iterator<Image> iterator = product.getImagesUrl().iterator(); iterator.hasNext();) {
			Image img = iterator.next();
			img.setProduct(product);
		}

	}

}
