package maikiencuong.aspects;

import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		newOrder.setCustomer(customerServ.findById(newOrder.getCustomer().getId()));

		List<OrderDetail> details = newOrder.getOrderDetails();
		for (Iterator<?> iterator = details.iterator(); iterator.hasNext();) {
			OrderDetail odd = (OrderDetail) iterator.next();
			SubProduct subProduct = subProductServ.findById(odd.getSubProduct().getId());
			if (subProduct != null) {
				odd.setSubProduct(subProduct);
				odd.setOrder(newOrder);
				if (subProduct.getInventory() >= odd.getQuantity()) {
					subProduct.setInventory(subProduct.getInventory() - odd.getQuantity());
					subProductServ.update(subProduct);
				} else
					throw new MyExcetion("Số lượng tồn của sản phẩm " + subProduct.getName() + " không đủ");
			} else {
				throw new MyExcetion("Sản phẩm " + odd.getSubProduct().getId() + " không tồn tại");
			}
		}
	}

}
