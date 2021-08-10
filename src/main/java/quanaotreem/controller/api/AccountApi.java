package quanaotreem.controller.api;

import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quanaotreem.dto.AccountDTO;
import quanaotreem.dto.CustomerDTO;
import quanaotreem.dto.update.AccountUpdateDTO;
import quanaotreem.entity.Account;
import quanaotreem.entity.Orderr;
import quanaotreem.entity.Role;
import quanaotreem.enumvalue.EnumRole;
import quanaotreem.response.MessageResponse;
import quanaotreem.service.AccountServ;
import quanaotreem.service.OrderServ;
import quanaotreem.service.impl.AccountDetailsImpl;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${cross.origin}", maxAge = 3600)
public class AccountApi {

    /**
     * khi dùng tính năng hot-reload trên Intelij idea cùng với spring-devtool,
     * khi run lại thì lỗi do không thể inject được model mapper qua field injection,
     * nên cần thêm setter để dùng setter injection
     */
    @Setter
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderServ orderServ;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AccountServ accountServ;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Find all.
     *
     * @return the response entity
     */
    @GetMapping("/accounts")
    public ResponseEntity<?> findAll() {
        List<Account> findAll = accountServ.findAll();
        if (!findAll.isEmpty()) {
            findAll.removeIf(x -> x.getCustomer() == null);
            List<AccountDTO> list = modelMapper.map(findAll, new TypeToken<List<AccountDTO>>() {
            }.getType());
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Danh sách tài khoản trống"));

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
     * Update account.
     *
     * @param accountUpdateDTO the account update DTO
     * @return the response entity
     * @Valid danh dau de cho spring kiem tra tinh hop le cua du lieu
     * @RequestBody danh dau de spring tu dong map body cua request sang DTO
     */
    @PutMapping("/account")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody AccountUpdateDTO accountUpdateDTO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    accountUpdateDTO.getUsername(), accountUpdateDTO.getOldPassword()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Tài khoản hoặc mật khẩu cũ không đúng"));
        }
        AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
        Account existsAccount = accountDetails.getAccount();
        existsAccount.setPassword(encoder.encode(accountUpdateDTO.getNewPassword()));

        if (accountServ.update(existsAccount) != null)
            return ResponseEntity.ok(new MessageResponse("Đổi mật khẩu thành công"));

        return ResponseEntity.badRequest().body(new MessageResponse("Đổi mật khẩu không thành công"));
    }

    /**
     * Delete account.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id) {
        Account account = accountServ.findById(id);
        try {
            if (account != null) {
                for (Role role : account.getRoles()) {
                    if (role.getName().equals(EnumRole.ROLE_ADMIN))
                        return ResponseEntity.ok(new MessageResponse("Không có quyền xóa tài khoản này"));
                }
                accountServ.delete(id);
                return ResponseEntity.ok(new MessageResponse("Xóa thành công tài khoản"));
            }
            return ResponseEntity.badRequest().body(new MessageResponse("Không tìm thấy tài khoản này"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(
                    "Xóa tài khoản không thành công. Chỉ xóa được khi khách hàng của tài khoản này chưa lập hóa đơn nào"));
        }

    }

    /**
     * Get the account and order.
     *
     * @param id the id
     * @return the account and order
     */
    @GetMapping("/account/order/{accountId}")
    public ResponseEntity<?> getAccountAndOrder(@PathVariable("accountId") Long id) {
        Account existsAccount = accountServ.findById(id);
        if (existsAccount != null && existsAccount.getCustomer() != null) {
            double sum = 0d;
            List<Orderr> ordersbyCustomerId = orderServ.findAllByCustomer_Id(existsAccount.getCustomer().getId());

            for (Orderr orderr : ordersbyCustomerId) {
                sum += orderr.sumTotal();
            }
            Map<String, Object> map = new HashMap<>();
            map.put("account", modelMapper.map(existsAccount, AccountDTO.class));
            map.put("customer", modelMapper.map(existsAccount.getCustomer(), CustomerDTO.class));
            map.put("countOrders", ordersbyCustomerId.size());
            map.put("sumOrders", sum);

            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Danh sách trống"));
    }

}