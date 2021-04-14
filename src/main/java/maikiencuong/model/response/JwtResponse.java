package maikiencuong.model.response;

import java.util.List;

import lombok.Getter;
import maikiencuong.entity.Customer;

@Getter
public class JwtResponse {

	private Long id;

	private String username;

	private String tokenType = "Bearer";

	private String accessToken;

	private String email;

	private boolean enable;

	private List<String> roles;

	private Customer customer;

	public JwtResponse(Long id, String username, String accessToken, String email, boolean enable, List<String> roles,
			Customer customer) {
		this.id = id;
		this.username = username;
		this.accessToken = accessToken;
		this.email = email;
		this.enable = enable;
		this.roles = roles;
		this.customer = customer;
	}

}