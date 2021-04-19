package maikiencuong.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import maikiencuong.entity.Order;
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
		Order order = (Order) joinPoint.getArgs()[0];
		order.setCustomer(customerServ.findById(order.getCustomer().getId()));
		order.getOrderDetails().forEach(x -> {
			x.setSubProduct(subProductServ.findById(x.getSubProduct().getId()));
			x.setOrder(order);
		});
		order.sumTotal();
	}

}
