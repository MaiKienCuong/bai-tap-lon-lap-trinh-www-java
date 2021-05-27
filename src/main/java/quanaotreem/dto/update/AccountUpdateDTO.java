package quanaotreem.dto.update;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Username không được để trống")
	private String username;

	@NotBlank(message = "Mật khẩu cũ không được để trống")
	private String oldPassword;

	@NotBlank(message = "Mật khẩu mới không được để trống")
	@Size(min = 6, max = 60, message = "Mật khẩu mới phải từ 6 đến 60 kí tự")
	private String newPassword;

}
