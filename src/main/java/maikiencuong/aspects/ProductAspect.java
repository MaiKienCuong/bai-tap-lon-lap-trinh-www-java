package maikiencuong.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import maikiencuong.entity.Category;
import maikiencuong.entity.Product;
import maikiencuong.entity.Supplier;
import maikiencuong.service.CategoryServ;
import maikiencuong.service.SupplierServ;

@Aspect
@Component
public class ProductAspect {

	@Autowired
	private CategoryServ categoryServ;

	@Autowired
	private SupplierServ supplierServ;

	@Before("execution(* maikiencuong.controller.api.ProductApi.addProduct(..))")
	public void beforeAddProduct(JoinPoint joinPoint) {
		Product product = (Product) joinPoint.getArgs()[0];

		Category category = categoryServ.findById(product.getCategory().getId());
		Supplier supplier = supplierServ.findById(product.getSupplier().getId());
		product.setCategory(category);
		product.setSupplier(supplier);

		product.getSubProducts().forEach(sub -> sub.setProduct(product));

	}

}
