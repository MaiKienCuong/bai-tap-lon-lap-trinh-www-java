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
		Customer customer = (Customer) joinPoint.getArgs()[0];
		Account account = customer.getAccount();
		if (accountServ.existsByUsername(account.getUsername())) {
			throw new MyExcetion("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác");
		}
		if (customerServ.existsByEmail(customer.getEmail())) {
			throw new MyExcetion("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác");
		}
		Set<Role> roles = account.getRoles().stream().map(item -> roleServ.findByName(item.getName()))
				.collect(Collectors.toSet());
		account.setRoles(roles);
		account.setCustomer(customer);
		account.setPassword(encoder.encode(account.getPassword()));
	}

	@Before("execution(* maikiencuong.controller.api.CustomerApi.addCustomer(..))")
	public void validAddCustomer(JoinPoint joinPoint) throws MyExcetion {
		validSignupCustomer(joinPoint);
	}

	@Before("execution(* maikiencuong.controller.api.CustomerApi.updateCustomer(..))")
	public void validUpdateCustomer(JoinPoint joinPoint) throws MyExcetion {
		Customer customer = (Customer) joinPoint.getArgs()[0];
		Account account = customer.getAccount();
		Account account2 = accountServ.findByUsername(account.getUsername());
		if (account2 != null && !account.getId().equals(account2.getId())) {
			throw new MyExcetion("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác");
		}
		Customer customer2 = customerServ.findByEmail(customer.getEmail());
		if (customer2 != null && !customer.getId().equals(customer2.getId())) {
			throw new MyExcetion("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác");
		}
		account.setCustomer(customer);
		account.setPassword(encoder.encode(account.getPassword()));
	}
}
