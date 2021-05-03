package maikiencuong.aspects;

import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import maikiencuong.entity.Customer;
import maikiencuong.entity.OrderDetail;
import maikiencuong.entity.Orderr;
import maikiencuong.entity.SubProduct;
import maikiencuong.handler.MyExcetion;
import maikiencuong.service.CustomerServ;
import maikiencuong.service.SubProductServ;

@Aspect
@Component
public class OrderAspect {

	@Autowired
	private CustomerServ customerServ;

	@Autowired
	private SubProductServ subProductServ;

	@Before("execution(* maikiencuong.controller.api.OrderApi.addOrder(..))")
	public void beforeAddOrder(JoinPoint joinPoint) throws MyExcetion {
		Orderr newOrder = (Orderr) joinPoint.getArgs()[0];
		Customer customer = customerServ.findById(newOrder.getCustomer().getId());
		if (customer == null)
			throw new MyExcetion("Không tìm thấy thông tin của khách hàng");
		newOrder.setCustomer(customer);

		List<OrderDetail> details = newOrder.getOrderDetails();
		for (Iterator<?> iterator = details.iterator(); iterator.hasNext();) {
			OrderDetail odd = (OrderDetail) iterator.next();
			SubProduct subProduct = subProductServ.findById(odd.getSubProduct().getId());
			if (subProduct != null) {
				odd.setSubProduct(subProduct);
				odd.setOrder(newOrder);
				if (odd.getQuantity() > subProduct.getInventory())
					throw new MyExcetion("Sản phẩm " + subProduct.getName() + " không đủ số lượng");
			} else
				throw new MyExcetion("Sản phẩm Id=" + odd.getSubProduct().getId() + " không tồn tại");
		}
	}

}
