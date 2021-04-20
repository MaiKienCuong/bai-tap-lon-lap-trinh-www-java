package maikiencuong.service;

import java.util.List;

import maikiencuong.entity.Role;
import maikiencuong.enumvalue.EnumRole;

public interface RoleServ {

	public Role findById(Long id);

	public Role findByName(EnumRole name);

	public List<Role> findAll();

}
