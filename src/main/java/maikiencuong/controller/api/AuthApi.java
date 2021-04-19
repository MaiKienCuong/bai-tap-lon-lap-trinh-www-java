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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.AccountCreateDTO;
import maikiencuong.dto.CustomerCreateDTO;
import maikiencuong.dto.JwtResponse;
import maikiencuong.dto.LoginDTO;
import maikiencuong.dto.MessageResponse;
import maikiencuong.entity.Account;
import maikiencuong.entity.Customer;
import maikiencuong.entity.EnumRole;
import maikiencuong.entity.Role;
import maikiencuong.security.jwt.JwtUtils;
import maikiencuong.service.AccountServ;
import maikiencuong.service.CustomerServ;
import maikiencuong.service.RoleServ;
import maikiencuong.service.impl.AccountDetailsImpl;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthApi {

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

	/**
	 * dang nhap
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody LoginDTO request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
		List<String> roles = accountDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(accountDetails.getId(), accountDetails.getUsername(), jwt,
				accountDetails.isEnabled(), roles, accountDetails.getCustomer()));
	}

	/**
	 * dang ky tai khoan cho khach hang
	 * 
	 * @param request
	 * @return
	 * @throws MyException
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody CustomerCreateDTO request) {
		AccountCreateDTO accountModel = request.getAccount();
		if (accountServ.existsByUsername(accountModel.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Username đã tồn tại trong hệ thống. Vui lòng chọn Username khác"));
		}
		// Create new user's account
		Account account = Account.builder().username(accountModel.getUsername())
				.password(encoder.encode(accountModel.getPassword())).build();
		Set<Role> roles = new HashSet<>();
		Role userRole = roleServ.findByName(EnumRole.ROLE_CUSTOMER);
		if (userRole == null)
			return ResponseEntity.badRequest().body(new MessageResponse("Role này chưa có trong database"));
		roles.add(userRole);
		account.setRoles(roles);
		Customer customer = Customer.builder().account(account).name(request.getName()).phone(request.getPhone())
				.email(request.getEmail()).build();
		Customer result = customerServ.add(customer);
		if (result != null)
			return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công"));
		return ResponseEntity.badRequest().body(new MessageResponse("Đăng ký không thành công"));
	}

}