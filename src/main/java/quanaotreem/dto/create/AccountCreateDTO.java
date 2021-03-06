package quanaotreem.dto.create;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Username không được để trống")
	@Size(min = 3, max = 60, message = "Username phải từ 3 đến 60 ký tự")
	private String username;

	@NotBlank(message = "Mật khẩu không được để trống")
	@Size(min = 6, max = 60, message = "Mật khẩu phải từ 6 đến 60 kí tự")
	private String password;

}
