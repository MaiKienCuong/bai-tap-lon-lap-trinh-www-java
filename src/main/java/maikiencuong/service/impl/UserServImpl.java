package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.User;
import maikiencuong.repository.UserRepo;
import maikiencuong.service.UserServ;

@Service
public class UserServImpl implements UserServ {

	@Autowired
	private UserRepo userRepo;

	@Override
	@Transactional
	public User findById(Long id) {
		Optional<User> optional = userRepo.findById(id);
		return !optional.isEmpty() ? optional.get() : null;
	}

	@Override
	@Transactional
	public User add(User user) {
		return userRepo.save(user);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	@Transactional
	public User update(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	@Transactional
	public Page<User> findAllByNameLike(String name, Pageable pageable) {
		return userRepo.findAllByNameLike(name, pageable);
	}

	@Override
	@Transactional
	public Page<User> findAllByPhoneLike(String phone, Pageable pageable) {
		return userRepo.findAllByPhoneLike(phone, pageable);
	}

	@Override
	@Transactional
	public Page<User> findAllByIdentityCardLike(String identityCard, Pageable pageable) {
		return userRepo.findAllByIdentityCardLike(identityCard, pageable);
	}

	@Override
	@Transactional
	public Page<User> findAll(Pageable pageable) {
		return userRepo.findAll(pageable);
	}

}
