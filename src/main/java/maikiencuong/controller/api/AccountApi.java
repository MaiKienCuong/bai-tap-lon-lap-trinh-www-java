package maikiencuong.controller.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.AccountDTO;
import maikiencuong.dto.update.AccountUpdateDTO;
import maikiencuong.entity.Account;
import maikiencuong.entity.Orderr;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.AccountServ;
import maikiencuong.service.OrderServ;
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
	private OrderServ orderServ;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Update account.
	 * 
	 * @Valid danh dau de cho spring kiem tra tinh hop le cua du lieu
	 * @RequestBody danh dau de spring tu dong map body cua request sang DTO
	 *
	 * @param accountUpdateDTO the account update DTO
	 * @return the response entity
	 */
	@PutMapping("/account")
	public ResponseEntity<?> updateAccount(@Valid @RequestBody AccountUpdateDTO accountUpdateDTO) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					accountUpdateDTO.getUsername(), accountUpdateDTO.getOldPassword()));
			AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
			Account existsAccount = accountDetails.getAccount();
			existsAccount.setPassword(encoder.encode(accountUpdateDTO.getNewPassword()));
			if (accountServ.update(existsAccount) != null)
				return ResponseEntity.ok(new MessageResponse("Đổi mật khẩu thành công"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Tài khoản hoặc mật khẩu cũ không đúng"));
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Đổi mật khẩu không thành công"));
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/account/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Account existsAccount = accountServ.findById(id);
		if (existsAccount != null)
			return ResponseEntity.ok(existsAccount);
		return ResponseEntity.badRequest().body(new MessageResponse("Không tìm thấy tài khoản nào"));

	}

	/**
	 * Get the account and order.
	 *
	 * @param id the id
	 * @return the account and order
	 */
	@GetMapping("/account/order/{id}")
	public ResponseEntity<?> getAccountAndOrder(@PathVariable("id") Long id) {

		Account existsAccount = accountServ.findById(id);
		if (existsAccount.getCustomer() != null) {
			Double sum = Double.valueOf(0d);
			List<Orderr> ordersbyCustomerId = orderServ.findAllByCustomer_Id(existsAccount.getCustomer().getId());
			for (Iterator<Orderr> iterator = ordersbyCustomerId.iterator(); iterator.hasNext();) {
				sum += iterator.next().sumTotal();
			}
			Map<String, Object> map = new HashMap<>();
			map.put("account", modelMapper.map(existsAccount, AccountDTO.class));
			map.put("customer", existsAccount.getCustomer());
			map.put("countOrders", ordersbyCustomerId.size());
			map.put("sumOrders", sum);

			return ResponseEntity.ok().body(map);
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Danh sách trống"));
	}

}