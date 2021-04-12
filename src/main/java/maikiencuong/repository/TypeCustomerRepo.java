package maikiencuong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.EnumTypeCustomer;
import maikiencuong.entity.TypeCustomer;

@Repository
public interface TypeCustomerRepo extends JpaRepository<TypeCustomer, Long> {

	public Optional<TypeCustomer> findByType(EnumTypeCustomer type);

}
