package quanaotreem.controller.api;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import quanaotreem.dto.AccountDTO;
import quanaotreem.dto.CustomerDTO;
import quanaotreem.dto.create.CustomerCreateDTO;
import quanaotreem.dto.mapper.DTO;
import quanaotreem.entity.Account;
import quanaotreem.entity.Customer;
import quanaotreem.entity.Role;
import quanaotreem.handler.MyException;
import quanaotreem.jwt.JwtUtils;
import quanaotreem.request.LoginRequest;
import quanaotreem.response.JwtResponse;
import quanaotreem.response.MessageResponse;
import quanaotreem.service.AccountServ;
import quanaotreem.service.CustomerServ;
import quanaotreem.service.RoleServ;
import quanaotreem.service.impl.AccountDetailsImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${cross.origin}", maxAge = 3600)
public class AuthApi {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleServ roleServ;

	@Autowired
	private AccountServ accountServ;

	@Autowired
	private CustomerServ customerServ;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Signin.
	 *
	 * @param loginRequest the login request
	 * @return the response entity
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("????ng nh???p kh??ng th??nh c??ng. T??i kho???n ho???c m???t kh???u kh??ng ????ng"));
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
		AccountDTO accountDTO = modelMapper.map(accountDetails.getAccount(), AccountDTO.class);
		Customer customer = accountDetails.getAccount().getCustomer();

		return ResponseEntity.ok(new JwtResponse(jwt,
				customer != null ? modelMapper.map(customer, CustomerDTO.class) : null, accountDTO));
	}

	/**
	 * Signup.
	 * 
	 * @DTO danh dau body cua request gui len la doi tuong DTO, nhung argument co
	 *      annotation DTO se duoc tu dong chuyen qua entity
	 *
	 * @param newCustomer the new customer
	 * @return the response entity
	 * @throws MyException
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@DTO(CustomerCreateDTO.class) Customer newCustomer) throws MyException {
		Account newAccount = newCustomer.getAccount();
		if (accountServ.existsByUsername(newAccount.getUsername()))
			throw new MyException("Username ???? t???n t???i trong h??? th???ng. Vui l??ng ch???n Username kh??c");
		if (customerServ.existsByEmail(newCustomer.getEmail()))
			throw new MyException("Email ???? t???n t???i trong h??? th???ng. Vui l??ng ch???n Email kh??c");

		Set<Role> roles = newAccount.getRoles().stream().map(item -> roleServ.findByName(item.getName()))
				.collect(Collectors.toSet());
		newAccount.setRoles(roles);
		newAccount.setCustomer(newCustomer);
		newAccount.setPassword(encoder.encode(newAccount.getPassword()));

		if (customerServ.add(newCustomer) != null)
			return ResponseEntity.ok(new MessageResponse("????ng k?? t??i kho???n th??nh c??ng"));

		return ResponseEntity.badRequest().body(new MessageResponse("????ng k?? kh??ng th??nh c??ng"));
	}

}