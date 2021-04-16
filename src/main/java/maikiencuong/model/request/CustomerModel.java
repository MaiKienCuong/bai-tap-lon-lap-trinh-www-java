package maikiencuong.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerModel {

	private Long id;

	@NotBlank(message = "Tên khách hàng không được để trống")
	private String name;

	@NotBlank(message = "Số điện thoại không được để trống")
	@Pattern(regexp = "[0-9]{10,11}", message = "Số điện thoại phải chứa 10-11 chữ số")
	private String phone;

	@Valid
	@NotNull(message = "Chưa có thông tin tài khoản")
	private AccountModel account;

}
