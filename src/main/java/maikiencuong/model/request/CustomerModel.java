package maikiencuong.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerModel {

	private Long id;

	@NotBlank(message = "Tên khách hàng không được để trống")
	private String name;

	private String phone;

	@Valid
	private AccountModel account;

}
