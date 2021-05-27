package quanaotreem.service;

import java.util.List;

import quanaotreem.entity.Role;
import quanaotreem.enumvalue.EnumRole;

public interface RoleServ {

	public Role findById(Long id);

	public Role findByName(EnumRole name);

	public List<Role> findAll();

}
