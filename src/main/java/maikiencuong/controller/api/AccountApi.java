package maikiencuong.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.update.AccountUpdateDTO;
import maikiencuong.entity.Account;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.AccountServ;
import maikiencuong.service.impl.AccountDetailsImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountApi {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AccountServ accountServ;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PutMapping("/account")
	public ResponseEntity<?> updateAccount(@Valid @RequestBody AccountUpdateDTO accountDto) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getOldPassword()));
			AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
			Account existsAccount = accountDetails.getAccount();
			existsAccount.setPassword(encoder.encode(accountDto.getNewPassword()));
			if (accountServ.update(existsAccount) != null)
				return ResponseEntity.ok(new MessageResponse("Đổi mật khẩu thành công"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu cũ không đúng"));
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Đổi mật khẩu không thành công"));
	}

}