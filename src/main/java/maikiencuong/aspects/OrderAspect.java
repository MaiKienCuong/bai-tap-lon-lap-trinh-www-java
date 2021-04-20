package maikiencuong.aspects;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import maikiencuong.entity.OrderDetail;
import maikiencuong.entity.Orderr;
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
	public void beforeAddOrder(JoinPoint joinPoint) {
		Orderr newOrder = (Orderr) joinPoint.getArgs()[0];
		newOrder.setCustomer(customerServ.findById(newOrder.getCustomer().getId()));

		List<OrderDetail> details = newOrder.getOrderDetails();
		details.forEach(x -> {
			x.setSubProduct(subProductServ.findById(x.getSubProduct().getId()));
			x.setOrder(newOrder);
		});
		details.removeIf(o -> o.getSubProduct() == null);
	}

}
