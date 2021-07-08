package quanaotreem.service;

import java.util.List;

import quanaotreem.entity.Role;
import quanaotreem.enumvalue.EnumRole;

public interface RoleServ {

	Role findById(Long id);

	Role findByName(EnumRole name);

	List<Role> findAll();

}
