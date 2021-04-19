package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Role findByName(EnumRole name) {
		Optional<Role> optional = roleRepo.findByName(name);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	@Transactional
	public Page<Role> findAll(Pageable pageable) {
		return roleRepo.findAll(pageable);
	}

	@Override
	@Transactional
	public Role findById(Long id) {
		Optional<Role> optional = roleRepo.findById(id);
		return optional.isEmpty() ? optional.get() : null;
	}

}
