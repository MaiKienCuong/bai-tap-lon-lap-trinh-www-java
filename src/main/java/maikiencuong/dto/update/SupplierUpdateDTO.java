package maikiencuong.dto.update;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message = "Id nhà cung cấp không được để trống")
	private Long id;

	@NotBlank(message = "Tên không được để trống")
	private String name;

	@Email(message = "Email không hợp lệ")
	@NotBlank(message = "Email không được để trống")
	private String email;

	@NotBlank(message = "Số điện thoại không được để trống")
	@Pattern(regexp = "[0-9]{10,11}", message = "Số điện thoại phải chứa 10-11 chữ số")
	private String phone;

	private String address;

}
