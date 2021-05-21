package maikiencuong.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import maikiencuong.entity.Customer;
import maikiencuong.entity.Orderr;

@Aspect
@Component
public class SendEmailAspect {

	@AfterReturning(pointcut = "execution(* maikiencuong.controller.api.AuthApi.signup(..))", returning = "responseEntity")
	public void afterSignup(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		Customer customer = (Customer) joinPoint.getArgs()[0];
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			System.out.println("==================");
			System.out.println("after sigup sending email to: " + customer.getEmail());
			System.out.println("==================");
		}
	}

	@AfterReturning(pointcut = "execution(* maikiencuong.controller.api.CustomerApi.addCustomer(..))", returning = "responseEntity")
	public void afterAddCustomer(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		Customer customer = (Customer) joinPoint.getArgs()[0];
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			System.out.println("==================");
			System.out.println("after add customer sending email to: " + customer.getEmail());
			System.out.println("==================");
		}
	}

	@AfterReturning(pointcut = "execution(* maikiencuong.controller.api.OrderApi.addOrder(..))", returning = "responseEntity")
	public void afterAddOrder(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		Orderr order = (Orderr) joinPoint.getArgs()[0];
		Customer customer = order.getCustomer();
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			System.out.println("==================");
			System.out.println("after add order sending email to: " + customer.getEmail());
			System.out.println("==================");
		}
	}

}
