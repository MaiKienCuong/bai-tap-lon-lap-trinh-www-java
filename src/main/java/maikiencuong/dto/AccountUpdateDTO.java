package maikiencuong.dto;

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

	@NotBlank(message = "Mật khẩu không được để trống")
	@Size(min = 6, max = 60, message = "Mật khẩu phải từ 6 đến 60 kí tự")
	private String password;

	private boolean enable;

}
