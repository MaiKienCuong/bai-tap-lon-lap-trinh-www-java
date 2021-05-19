package maikiencuong.payload.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.dto.AccountDTO;
import maikiencuong.dto.CustomerDTO;

/**
 * The Class JwtResponse.
 * 
 * <p>
 * Sau khi dang nhap thanh cong thi tra ve cho Client doi tuong cua class nay
 * </p>
 */
@Getter
@Setter
public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonUnwrapped
	private AccountDTO account;

	private CustomerDTO customer;

	private String tokenType;

	private String accessToken;

	public JwtResponse(String accessToken, CustomerDTO customer, AccountDTO account) {
		this.accessToken = accessToken;
		this.customer = customer;
		this.account = account;
		tokenType = "Bearer";
	}

}