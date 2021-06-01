package quanaotreem.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String phone;

	private String email;

	private String address;

	@JsonIgnore
//	bo qua thuoc tinh nay khi dua ra json
	private AccountDTO account;

}
