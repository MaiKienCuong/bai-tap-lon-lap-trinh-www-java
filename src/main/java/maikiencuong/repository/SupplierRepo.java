package maikiencuong.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Supplier;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {

	public Page<Supplier> findAllByNameLikeOrPhoneLike(String name, String phone, Pageable pageable);

	public List<Supplier> findAllByNameLikeOrPhoneLike(String name, String phone);

}
