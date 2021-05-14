package maikiencuong.controller.api;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.AccountDTO;
import maikiencuong.dto.CustomerDTO;
import maikiencuong.dto.create.CustomerCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.entity.Customer;
import maikiencuong.jwt.JwtUtils;
import maikiencuong.payload.request.LoginRequest;
import maikiencuong.payload.response.JwtResponse;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.CustomerServ;
import maikiencuong.service.impl.AccountDetailsImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthApi {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerServ customerServ;

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
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();

			AccountDTO accountDTO = modelMapper.map(accountDetails.getAccount(), AccountDTO.class);
			Customer customer = accountDetails.getAccount().getCustomer();
			if (customer != null)
				return ResponseEntity
						.ok(new JwtResponse(jwt, modelMapper.map(customer, CustomerDTO.class), accountDTO));
			return ResponseEntity.ok(new JwtResponse(jwt, null, accountDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Đăng nhập không thành công. Tài khoản hoặc mật khẩu không đúng"));
		}
	}

	/**
	 * Signup.
	 *
	 * @param newCustomer the new customer
	 * @return the response entity
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@DTO(CustomerCreateDTO.class) Customer newCustomer) {
		if (customerServ.add(newCustomer) != null)
			return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công"));

		return ResponseEntity.badRequest().body(new MessageResponse("Đăng ký không thành công"));
	}

}