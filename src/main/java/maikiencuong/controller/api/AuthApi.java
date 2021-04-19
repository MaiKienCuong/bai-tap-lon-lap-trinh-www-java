package maikiencuong.controller.api;

import java.util.List;
import java.util.stream.Collectors;

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

import maikiencuong.dto.CustomerDTO;
import maikiencuong.dto.JwtResponse;
import maikiencuong.dto.LoginDTO;
import maikiencuong.dto.MessageResponse;
import maikiencuong.dto.create.CustomerCreateDTO;
import maikiencuong.dto.mapper.DTO;
import maikiencuong.entity.Customer;
import maikiencuong.security.jwt.JwtUtils;
import maikiencuong.service.CustomerServ;
import maikiencuong.service.impl.AccountDetailsImpl;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthApi {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	CustomerServ customerServ;

	@Autowired
	private AuthenticationManager authenticationManager;

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
		if (accountDetails.getCustomer() != null)
			return ResponseEntity.ok(new JwtResponse(accountDetails.getId(), accountDetails.getUsername(), jwt,
					accountDetails.isEnabled(), roles,
					modelMapper.map(accountDetails.getCustomer(), CustomerDTO.class)));
		return ResponseEntity.ok(new JwtResponse(accountDetails.getId(), accountDetails.getUsername(), jwt,
				accountDetails.isEnabled(), roles, null));
	}

	/**
	 * dang ky tai khoan cho khach hang
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@DTO(CustomerCreateDTO.class) Customer customer) {
		Customer result = customerServ.add(customer);
		if (result != null)
			return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công"));
		return ResponseEntity.badRequest().body(new MessageResponse("Đăng ký không thành công"));

	}

}