package maikiencuong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.User;

public interface UserServ {

	public User findById(Long id);

	public User add(User user);

	public void delete(Long id);

	public User update(User user);

	public Page<User> findAll(Pageable pageable);

	public Page<User> findAllByNameLike(String name, Pageable pageable);

	public Page<User> findAllByPhoneLike(String phone, Pageable pageable);

	public Page<User> findAllByIdentityCardLike(String identityCard, Pageable pageable);
}
