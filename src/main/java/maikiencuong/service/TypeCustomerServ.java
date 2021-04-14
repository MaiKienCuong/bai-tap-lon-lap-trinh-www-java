package maikiencuong.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.EnumTypeCustomer;
import maikiencuong.entity.TypeCustomer;

public interface TypeCustomerServ {

	public Page<TypeCustomer> findAll(Pageable pageable);

	public TypeCustomer findById(Long id);

	public TypeCustomer add(TypeCustomer typeCustomer);

	public void delete(Long id);

	public TypeCustomer update(TypeCustomer typeCustomer);

	public Optional<TypeCustomer> findByType(EnumTypeCustomer type);

}
