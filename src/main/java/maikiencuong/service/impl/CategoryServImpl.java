package maikiencuong.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maikiencuong.entity.Category;
import maikiencuong.repository.CategoryRepo;
import maikiencuong.service.CategoryServ;

@Service
public class CategoryServImpl implements CategoryServ {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	@Transactional
	public Category findById(Long id) {
		Optional<Category> optional = categoryRepo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	@Transactional
	public Category add(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		categoryRepo.deleteById(id);
	}

	@Override
	@Transactional
	public Category update(Category category) {
		return categoryRepo.saveAndFlush(category);
	}

	@Override
	@Transactional
	public Page<Category> findAllByNameLike(String name, Pageable pageable) {
		return categoryRepo.findAllByNameLike("%" + name + "%", pageable);
	}

	@Override
	@Transactional
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepo.findAll(pageable);
	}

}
