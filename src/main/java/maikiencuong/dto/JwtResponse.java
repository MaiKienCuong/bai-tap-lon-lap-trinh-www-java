package maikiencuong.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String tokenType = "Bearer";

	private String accessToken;

	private boolean enable;

	private List<String> roles;

	private CustomerDTO customer;

	public JwtResponse(Long id, String username, String accessToken, boolean enable, List<String> roles,
			CustomerDTO customer) {
		this.id = id;
		this.username = username;
		this.accessToken = accessToken;
		this.enable = enable;
		this.roles = roles;
		this.customer = customer;
	}

}