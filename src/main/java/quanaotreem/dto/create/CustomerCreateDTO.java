package quanaotreem.dto.create;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Tên khách hàng không được để trống")
	@Size(max = 255, message = "Tên khách hàng không được vượt quá 255 ký tự")
	private String name;

	@NotBlank(message = "Số điện thoại không được để trống")
	@Pattern(regexp = "[0-9]{10,11}", message = "Số điện thoại phải chứa 10-11 chữ số")
	private String phone;

	@Email(message = "Email không hợp lệ")
	@NotBlank(message = "Email không được để trống")
	@Size(max = 255, message = "Email không được vượt quá 255 ký tự")
	private String email;

	@Size(max = 500, message = "Địa chỉ không được vượt quá 500 ký tự")
	private String address;

	@Valid
	@NotNull(message = "Chưa có thông tin tài khoản")
	private AccountCreateDTO account;

}
