package maikiencuong.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String address;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String phone;

}
