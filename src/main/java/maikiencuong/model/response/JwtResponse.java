package maikiencuong.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.entity.Customer;
import maikiencuong.entity.User;

public class JwtResponse {
	@Getter
	@Setter
	private String accessToken;

	@Getter
	@Setter
	private String tokenType = "Bearer";

	@Getter
	private Long id;

	@Getter
	private String username;

	@Getter
	private Customer customer;

	@Getter
	private User user;

	@Getter
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, Customer customer, User user, List<String> roles) {
		this.accessToken = accessToken;
		this.id = id;
		this.username = username;
		this.customer = customer;
		this.user = user;
		this.roles = roles;
	}

}