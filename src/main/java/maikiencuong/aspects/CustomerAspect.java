package maikiencuong.aspects;

import java.util.Set;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
	RoleServ roleServ;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private AccountServ accountServ;

	@Autowired
	private CustomerServ customerServ;

	@Before("execution(* maikiencuong.controller.api.AuthApi.signup(..))")
	public void validSignupCustomer(JoinPoint joinPoint) throws MyExcetion {
		Customer newCustomer = (Customer) joinPoint.getArgs()[0];
		Account newAccount = newCustomer.getAccount();
		if (accountServ.existsByUsername(newAccount.getUsername())) {
			throw new MyExcetion("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác");
		}
		if (customerServ.existsByEmail(newCustomer.getEmail())) {
			throw new MyExcetion("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác");
		}
		Set<Role> roles = newAccount.getRoles().stream().map(item -> roleServ.findByName(item.getName()))
				.collect(Collectors.toSet());
		newAccount.setRoles(roles);
		newAccount.setCustomer(newCustomer);
		newAccount.setPassword(encoder.encode(newAccount.getPassword()));
	}

	@Before("execution(* maikiencuong.controller.api.CustomerApi.addCustomer(..))")
	public void validAddCustomer(JoinPoint joinPoint) throws MyExcetion {
		validSignupCustomer(joinPoint);
	}

	@Before("execution(* maikiencuong.controller.api.CustomerApi.updateCustomer(..))")
	public void validUpdateCustomer(JoinPoint joinPoint) throws MyExcetion {
		Customer updateCustomer = (Customer) joinPoint.getArgs()[0];
		Account updateAccount = updateCustomer.getAccount();
		Account existsAccount = accountServ.findByUsername(updateAccount.getUsername());
		System.out.println(updateCustomer);
		System.out.println(updateAccount);
		System.out.println(existsAccount);
		if (existsAccount != null && !updateAccount.equals(existsAccount)) {
			throw new MyExcetion("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác");
		}
		Customer existsCustomer = customerServ.findByEmail(updateCustomer.getEmail());
		if (existsCustomer != null && !updateCustomer.equals(existsCustomer)) {
			throw new MyExcetion("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác");
		}
		updateAccount.setCustomer(updateCustomer);
		updateAccount.setPassword(encoder.encode(updateAccount.getPassword()));
	}
}
