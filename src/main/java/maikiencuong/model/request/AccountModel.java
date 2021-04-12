package maikiencuong.model.request;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountModel {

	private String username;

	private String password;

	private Set<String> role;

}
