package quanaotreem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quanaotreem.entity.SubProduct;
import quanaotreem.repository.SubProductRepo;
import quanaotreem.service.SubProductServ;

@Service
public class SubProductServImpl implements SubProductServ {

	@Autowired
	private SubProductRepo subProductRepo;

	@Override
	@Transactional
	public SubProduct add(SubProduct subProduct) {
		return subProductRepo.save(subProduct);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		subProductRepo.deleteById(id);
	}

	@Override
	@Transactional
	public SubProduct update(SubProduct subProduct) {
		return subProductRepo.save(subProduct);
	}

	@Override
	@Transactional
	public SubProduct findById(Long id) {
		Optional<SubProduct> optional = subProductRepo.findById(id);
		return optional.orElse(null);
	}

	@Override
	@Transactional
	public List<SubProduct> findAllByProduct_Id(Long id) {
		return subProductRepo.findAllByProduct_IdOrderBySize(id);
	}

	@Override
	@Transactional
	public List<SubProduct> findAllByProduct_IdAndColor(Long id, String color) {
		return subProductRepo.findAllByProduct_IdAndColorOrderBySize(id, color);
	}

	@Override
	@Transactional
	public List<SubProduct> findAllByProduct_IdAndColorAndSize(Long id, String color, String size) {
		return subProductRepo.findAllByProduct_IdAndColorAndSize(id, color, size);
	}

}
