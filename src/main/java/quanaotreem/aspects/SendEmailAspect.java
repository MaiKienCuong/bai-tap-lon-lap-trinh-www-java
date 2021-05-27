package quanaotreem.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import quanaotreem.entity.Customer;
import quanaotreem.entity.Orderr;
import quanaotreem.mail.Mail;
import quanaotreem.mail.MailService;

@Aspect
@Component
public class SendEmailAspect {

	@Autowired
	private MailService mailService;

	@Value("${spring.mail.username}")
	private String mailFrom;

	@AfterReturning(pointcut = "execution(* quanaotreem.controller.api.AuthApi.signup(..))", returning = "responseEntity")
	public void afterSignup(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		Customer customer = (Customer) joinPoint.getArgs()[0];
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			new Thread(() -> {
				Mail mail = new Mail();
				mail.setMailFrom(mailFrom);
				mail.setMailTo(customer.getEmail());
				mail.setMailSubject("Đăng ký tài khoản thành công");
				mail.setMailContent("Đăng ký tài khoản thành công!\n\nĐăng nhập tại\nhttps://localhost:8080");
				mailService.sendEmail(mail);
			}).start();
		}
	}

	@AfterReturning(pointcut = "execution(* quanaotreem.controller.api.CustomerApi.addCustomer(..))", returning = "responseEntity")
	public void afterAddCustomer(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		Customer customer = (Customer) joinPoint.getArgs()[0];
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			new Thread(() -> {
				Mail mail = new Mail();
				mail.setMailFrom(mailFrom);
				mail.setMailTo(customer.getEmail());
				mail.setMailSubject("Đăng ký tài khoản thành công");
				mail.setMailContent("Đăng ký tài khoản thành công!\n\nĐăng nhập tại\nhttps://localhost:8080");
				mailService.sendEmail(mail);
			}).start();
		}
	}

	@AfterReturning(pointcut = "execution(* quanaotreem.controller.api.OrderApi.addOrder(..))", returning = "responseEntity")
	public void afterAddOrder(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		Orderr order = (Orderr) joinPoint.getArgs()[0];
		Customer customer = order.getCustomer();
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			new Thread(() -> {
				Mail mail = new Mail();
				mail.setMailFrom(mailFrom);
				mail.setMailTo(customer.getEmail());
				mail.setMailSubject("Đặt hàng thành công");
				mail.setMailContent("Đặt hàng thành công!");
				mailService.sendEmail(mail);
			}).start();
		}
	}

}
