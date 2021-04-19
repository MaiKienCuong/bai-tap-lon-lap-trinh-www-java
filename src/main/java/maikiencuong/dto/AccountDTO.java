package maikiencuong.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import maikiencuong.entity.Role;

@Getter
@Setter
public class AccountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private boolean enable;
	
	private Set<Role> roles;

}
