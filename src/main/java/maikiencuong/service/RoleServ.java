package maikiencuong.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.EnumRole;
import maikiencuong.entity.Role;

public interface RoleServ {

	public Role findById(Long id);

	public Optional<Role> findByName(EnumRole name);

	public Page<Role> findAll(Pageable pageable);

}
