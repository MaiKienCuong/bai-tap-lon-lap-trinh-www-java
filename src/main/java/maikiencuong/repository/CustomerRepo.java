package maikiencuong.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	public Page<Customer> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable);

	public boolean existsByEmail(String email);

	public Optional<Customer> findByEmail(String email);

}
