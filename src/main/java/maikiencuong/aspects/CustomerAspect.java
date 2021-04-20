package maikiencuong.aspects;

import java.util.Set;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import maikiencuong.entity.Account;
import maikiencuong.entity.Customer;
import maikiencuong.entity.Role;
import maikiencuong.handler.MyExcetion;
import maikiencuong.service.AccountServ;
import maikiencuong.service.CustomerServ;
import maikiencuong.service.RoleServ;

@Aspect
@Component
public class CustomerAspect {

	@Autowired
	private RoleServ roleServ;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AccountServ accountServ;

	@Autowired
	private CustomerServ customerServ;

	@Pointcut("execution(* maikiencuong.controller.api.AuthApi.signup(..))")
	public void validSignupCustomer() {

	}

	@Pointcut("execution(* maikiencuong.controller.api.CustomerApi.addCustomer(..))")
	public void validAddCustomer() {

	}

	@Before("validSignupCustomer() || validAddCustomer()")
	public void validCustomer(JoinPoint joinPoint) throws MyExcetion {
		Customer newCustomer = (Customer) joinPoint.getArgs()[0];
		Account newAccount = newCustomer.getAccount();
		if (accountServ.existsByUsername(newAccount.getUsername()))
			throw new MyExcetion("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác");
		if (customerServ.existsByEmail(newCustomer.getEmail()))
			throw new MyExcetion("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác");

		Set<Role> roles = newAccount.getRoles().stream().map(item -> roleServ.findByName(item.getName()))
				.collect(Collectors.toSet());
		newAccount.setRoles(roles);
		newAccount.setCustomer(newCustomer);
		newAccount.setPassword(encoder.encode(newAccount.getPassword()));
	}

	@Before("execution(* maikiencuong.controller.api.CustomerApi.updateCustomer(..))")
	public void validUpdateCustomer(JoinPoint joinPoint) throws MyExcetion {

		Customer updateCustomer = (Customer) joinPoint.getArgs()[0];
		Customer existsCustomer = customerServ.findByEmail(updateCustomer.getEmail());
		if (existsCustomer != null && !existsCustomer.equals(updateCustomer))
			throw new MyExcetion("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác");
	}

}
