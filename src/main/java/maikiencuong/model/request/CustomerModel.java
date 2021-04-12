package maikiencuong.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerModel {

	private Long id;

	private String name;

	private String phone;

	private String email;

	private AccountModel account;

}
