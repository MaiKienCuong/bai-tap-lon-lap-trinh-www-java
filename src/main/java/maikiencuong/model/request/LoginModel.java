package maikiencuong.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginModel {
	
	@NotBlank
	private String username;

	@NotBlank
	private String password;

}