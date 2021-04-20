package maikiencuong.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Role;
import maikiencuong.enumvalue.EnumRole;
import maikiencuong.repository.RoleRepo;
import maikiencuong.service.RoleServ;

@Service
public class RoleServImpl implements RoleServ {

	@Autowired
	private RoleRepo roleRepo;

	@Override
	@Transactional
	public Role findByName(EnumRole name) {
		Optional<Role> optional = roleRepo.findByName(name);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	@Transactional
	public List<Role> findAll() {
		return roleRepo.findAll();
	}

	@Override
	@Transactional
	public Role findById(Long id) {
		Optional<Role> optional = roleRepo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

}
