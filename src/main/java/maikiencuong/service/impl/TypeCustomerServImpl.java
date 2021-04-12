package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.EnumTypeCustomer;
import maikiencuong.entity.TypeCustomer;
import maikiencuong.repository.TypeCustomerRepo;
import maikiencuong.service.TypeCustomerServ;

@Service
public class TypeCustomerServImpl implements TypeCustomerServ {

	@Autowired
	private TypeCustomerRepo typeCustomerRepo;

	@Override
	@Transactional
	public TypeCustomer findById(Long id) {
		Optional<TypeCustomer> optional = typeCustomerRepo.findById(id);
		return !optional.isEmpty() ? optional.get() : null;
	}

	@Override
	@Transactional
	public TypeCustomer add(TypeCustomer typeCustomer) {
		return typeCustomerRepo.save(typeCustomer);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		typeCustomerRepo.deleteById(id);
	}

	@Override
	@Transactional
	public TypeCustomer update(TypeCustomer typeCustomer) {
		return typeCustomerRepo.saveAndFlush(typeCustomer);
	}

	@Override
	@Transactional
	public Optional<TypeCustomer> findByType(EnumTypeCustomer type) {
		return typeCustomerRepo.findByType(type);
	}

}
