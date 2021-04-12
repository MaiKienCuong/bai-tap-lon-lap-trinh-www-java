package maikiencuong.model.request;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

	private Long id;

	private String name;

	private String identityCard;

	private String phone;

	private String email;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	private Date birthDate;

	private String nativeCountry;

	private String address;

	private AccountModel account;

}
