package maikiencuong.controller.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.entity.Account;
import maikiencuong.entity.Customer;
import maikiencuong.entity.EnumRole;
import maikiencuong.entity.EnumTypeCustomer;
import maikiencuong.entity.Role;
import maikiencuong.entity.TypeCustomer;
import maikiencuong.model.request.AccountModel;
import maikiencuong.model.request.CustomerModel;
import maikiencuong.model.request.LoginModel;
import maikiencuong.model.response.JwtResponse;
import maikiencuong.model.response.MessageResponse;
import maikiencuong.security.jwt.JwtUtils;
import maikiencuong.service.AccountServ;
import maikiencuong.service.CustomerServ;
import maikiencuong.service.RoleServ;
import maikiencuong.service.TypeCustomerServ;
import maikiencuong.service.impl.AccountDetailsImpl;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AccountServ accountServ;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleServ roleServ;

	@Autowired
	CustomerServ customerServ;

	@Autowired
	TypeCustomerServ typeCustomerServ;

	/**
	 * dang nhap
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody LoginModel request, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse(bindingResult.getFieldError().getDefaultMessage()));
		}
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
		List<String> roles = accountDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(accountDetails.getId(), accountDetails.getUsername(), jwt,
				accountDetails.getEmail(), accountDetails.isEnabled(), roles, accountDetails.getCustomer()));

	}

	/**
	 * dang ky tai khoan cho khach hang
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody CustomerModel request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse(bindingResult.getFieldError().getDefaultMessage()));
		}
		AccountModel accountModel = request.getAccount();
		if (accountServ.existsByUsername(accountModel.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác"));
		}

		if (accountServ.existsByEmail(accountModel.getEmail())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Email đã tồn tại trong hệ thống. Vui lòng chọn Email khác"));
		}

		// Create new user's account
		Account account = Account.builder().username(accountModel.getUsername()).email(accountModel.getEmail())
				.password(encoder.encode(accountModel.getPassword())).build();

		Set<Role> roles = new HashSet<>();
		Role userRole = roleServ.findByName(EnumRole.ROLE_CUSTOMER)
				.orElseThrow(() -> new RuntimeException("Role này chưa được lưu trong database"));
		roles.add(userRole);
		account.setRoles(roles);

		TypeCustomer typeCustomer = typeCustomerServ.findByType(EnumTypeCustomer.NONE)
				.orElseThrow(() -> new RuntimeException("Loại khách hàng này chưa được lưu trong database"));

		Customer customer = Customer.builder().account(account).name(request.getName()).phone(request.getPhone())
				.typeCustomer(typeCustomer).build();
		Customer result = customerServ.add(customer);

		return ResponseEntity.ok(result);

	}

}