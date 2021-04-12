package maikiencuong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	public Page<User> findAllByNameLike(String name, Pageable pageable);

	public Page<User> findAllByPhoneLike(String phone, Pageable pageable);

	public Page<User> findAllByIdentityCardLike(String identityCard, Pageable pageable);

}
