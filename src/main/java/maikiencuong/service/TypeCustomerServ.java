package maikiencuong.service;

import java.util.Optional;

import maikiencuong.entity.EnumTypeCustomer;
import maikiencuong.entity.TypeCustomer;

public interface TypeCustomerServ {

	public TypeCustomer findById(Long id);

	public TypeCustomer add(TypeCustomer typeCustomer);

	public void delete(Long id);

	public TypeCustomer update(TypeCustomer typeCustomer);

	public Optional<TypeCustomer> findByType(EnumTypeCustomer type);

}
