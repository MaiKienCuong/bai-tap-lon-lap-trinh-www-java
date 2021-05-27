package quanaotreem.aspects;

import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import quanaotreem.entity.Customer;
import quanaotreem.entity.OrderDetail;
import quanaotreem.entity.Orderr;
import quanaotreem.entity.SubProduct;
import quanaotreem.handler.MyException;
import quanaotreem.service.CustomerServ;
import quanaotreem.service.SubProductServ;

/**
 * The Class OrderAspect.
 * 
 * @Aspect danh dau day la mot class aspect, dung de pre-process truoc khi them
 *         mot hoa don moi vao database
 * 
 */
@Aspect
@Component
public class OrderAspect {

	@Autowired
	private CustomerServ customerServ;

	@Autowired
	private SubProductServ subProductServ;

	/**
	 * Before add order.
	 *
	 * <p>
	 * Phuong thuc nay se duoc thuc hien truoc khi phuong thuc addOrder trong
	 * controller duoc goi, dung de kiem tra tinh hop le cua hoa don
	 * </p>
	 *
	 * @param joinPoint the join point
	 * @throws MyException the my excetion
	 */
	@Before("execution(* quanaotreem.controller.api.OrderApi.addOrder(..))")
	public void beforeAddOrder(JoinPoint joinPoint) throws MyException {
		Orderr newOrder = (Orderr) joinPoint.getArgs()[0];
		Customer existsCustomer = customerServ.findById(newOrder.getCustomer().getId());
		if (existsCustomer == null)
			throw new MyException("Không tìm thấy thông tin của khách hàng");
		newOrder.setCustomer(existsCustomer);

		List<OrderDetail> orderDetails = newOrder.getOrderDetails();
		for (Iterator<?> iterator = orderDetails.iterator(); iterator.hasNext();) {
			OrderDetail detail = (OrderDetail) iterator.next();
			SubProduct subProduct = subProductServ.findById(detail.getSubProduct().getId());
			if (subProduct != null) {
				detail.setSubProduct(subProduct);
				detail.setOrder(newOrder);
				if (detail.getQuantity() > subProduct.getInventory())
					throw new MyException("Sản phẩm " + subProduct.getName() + " không còn đủ số lượng");
			} else
				throw new MyException("Không tìm thấy sản phẩm có Id= " + detail.getSubProduct().getId());
		}
	}

}
