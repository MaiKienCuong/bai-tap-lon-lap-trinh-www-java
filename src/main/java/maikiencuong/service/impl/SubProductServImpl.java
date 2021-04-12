package maikiencuong.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.InvenColorSize;
import maikiencuong.entity.SubProduct;
import maikiencuong.repository.SubProductRepo;
import maikiencuong.service.SubProductServ;

@Service
public class SubProductServImpl implements SubProductServ {

	@Autowired
	private SubProductRepo subProductRepo;

	@Override
	@Transactional
	public Set<InvenColorSize> sizeById(Long id) {
		Set<InvenColorSize> set = subProductRepo.sizeById(id);
		set.forEach(x -> {
			x.setColor(null);
			x.setInventory(null);
		});
		return new HashSet<>(set);
	}

	@Override
	@Transactional
	public Set<InvenColorSize> inventoryAndSizeByIdAndColor(Long id, String color) {
		return subProductRepo.inventoryAndSizeByIdAndColor(id, color);
	}

	@Override
	@Transactional
	public Set<InvenColorSize> invenColorSizeByIdAndColorAndSize(Long id, String color, String size) {
		return subProductRepo.invenColorSizeByIdAndColorAndSize(id, color, size);
	}

	@Override
	@Transactional
	public Set<InvenColorSize> colorById(Long id) {
		Set<InvenColorSize> set = subProductRepo.colorById(id);
		set.forEach(x -> {
			x.setSize(null);
			x.setInventory(null);
		});
		return new HashSet<>(set);
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
