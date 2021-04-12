package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.EnumRole;
import maikiencuong.entity.Role;
import maikiencuong.repository.RoleRepo;
import maikiencuong.service.RoleServ;

@Service
public class RoleServImpl implements RoleServ {

	@Autowired
	private RoleRepo roleRepo;

	@Override
	@Transactional
	public Optional<Role> findByName(EnumRole name) {
		return roleRepo.findByName(name);
	}

}
