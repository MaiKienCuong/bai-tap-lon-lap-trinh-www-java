package maikiencuong.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.dto.SizeColorInventory;
import maikiencuong.entity.SubProduct;
import maikiencuong.repository.SubProductRepo;
import maikiencuong.service.SubProductServ;

@Service
public class SubProductServImpl implements SubProductServ {

	@Autowired
	private SubProductRepo subProductRepo;

	@Override
	@Transactional
	public Set<SizeColorInventory> sizeById(Long id) {
		return subProductRepo.sizeById(id);
	}

	@Override
	@Transactional
	public Set<SizeColorInventory> inventoryAndSizeByIdAndColor(Long id, String color) {
		return subProductRepo.inventoryAndSizeByIdAndColor(id, color);
	}

	@Override
	@Transactional
	public Set<SizeColorInventory> invenColorSizeByIdAndColorAndSize(Long id, String color, String size) {
		return subProductRepo.invenColorSizeByIdAndColorAndSize(id, color, size);
	}

	@Override
	@Transactional
	public Set<SizeColorInventory> colorById(Long id) {
		return subProductRepo.colorById(id);
	}

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
		return subProductRepo.saveAndFlush(subProduct);
	}

	@Override
	@Transactional
	public SubProduct findById(Long id) {
		Optional<SubProduct> optional = subProductRepo.findById(id);
		return !optional.isEmpty() ? optional.get() : null;
	}

}
