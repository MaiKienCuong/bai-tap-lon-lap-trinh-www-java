package maikiencuong.service;

import java.util.Optional;

import maikiencuong.entity.EnumRole;
import maikiencuong.entity.Role;

public interface RoleServ {

	public Optional<Role> findByName(EnumRole name);

}
