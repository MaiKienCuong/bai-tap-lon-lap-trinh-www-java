package quanaotreem.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Username không được để trống")
	private String username;

	@NotBlank(message = "Mật khẩu không được để trống")
	private String password;

}