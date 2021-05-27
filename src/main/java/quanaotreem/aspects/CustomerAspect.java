package quanaotreem.aspects;

import java.util.Set;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import quanaotreem.entity.Account;
import quanaotreem.entity.Customer;
import quanaotreem.entity.Role;
import quanaotreem.handler.MyException;
import quanaotreem.service.AccountServ;
import quanaotreem.service.CustomerServ;
import quanaotreem.service.RoleServ;

/**
 * The Class CustomerAspect.
 * 
 * @Aspect danh dau day la mot class aspect, dung de pre-process cac phuong thuc
 *         dang ky tai khoan va them khach hang moi trong conntroller api
 */
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

	/**
	 * Before signup customer.
	 * 
	 * @Pointcut danh dau day la mot diem giao cat, no se duoc xen vao khi goi
	 *           phuong thuc signup trong controller
	 */
	@Pointcut("execution(* quanaotreem.controller.api.AuthApi.signup(..))")
	public void beforeSignupCustomer() {

	}

	/**
	 * Before add customer.
	 * 
	 * @Pointcut danh dau day la mot diem giao cat, no se duoc xen vao khi goi
	 *           phuong thuc addCutomer trong controller
	 */
	@Pointcut("execution(* quanaotreem.controller.api.CustomerApi.addCustomer(..))")
	public void beforeAddCustomer() {

	}

	/**
	 * Before add or signup customer.
	 * 
	 * </p>
	 * Khi phuong thuc signup hoac addCustomer trong controller duoc goi thi phuong
	 * thuc nay se duoc thuc hien truoc, dung de kiem tra tinh hop le cua du lieu
	 * </p>
	 * 
	 * @Before danh dau la no se duoc thuc hien truoc hai phuong thuc signup va
	 *         addCustomer trong controller
	 * @param joinPoint the join point
	 * @throws MyException the my excetion
	 */
	@Before("beforeAddCustomer() || beforeSignupCustomer()")
	public void beforeAddOrSignupCustomer(JoinPoint joinPoint) throws MyException {
		Customer newCustomer = (Customer) joinPoint.getArgs()[0];
		Account newAccount = newCustomer.getAccount();
		if (accountServ.existsByUsername(newAccount.getUsername()))
			throw new MyException("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác");
		if (customerServ.existsByEmail(newCustomer.getEmail()))
			throw new MyException("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác");

		Set<Role> roles = newAccount.getRoles().stream().map(item -> roleServ.findByName(item.getName()))
				.collect(Collectors.toSet());
		newAccount.setRoles(roles);
		newAccount.setCustomer(newCustomer);
		newAccount.setPassword(encoder.encode(newAccount.getPassword()));
	}

	/**
	 * Before update customer.
	 * 
	 * <p>
	 * Phuong thuc nay se duoc goi truoc khi thuc hien phuong thuc updateCustomer
	 * trong controller, dung de kiem tra tinh hop le cua du lieu
	 * </p>
	 * 
	 * @param joinPoint the join point
	 * @throws MyException the my excetion
	 */
	@Before("execution(* quanaotreem.controller.api.CustomerApi.updateCustomer(..))")
	public void beforeUpdateCustomer(JoinPoint joinPoint) throws MyException {
		Customer updateCustomer = (Customer) joinPoint.getArgs()[0];
		Customer existsCustomer = customerServ.findByEmail(updateCustomer.getEmail());
		if (existsCustomer != null && !existsCustomer.equals(updateCustomer))
			throw new MyException("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác");
	}

}
