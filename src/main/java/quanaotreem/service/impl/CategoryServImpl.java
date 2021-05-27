package quanaotreem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quanaotreem.entity.Category;
import quanaotreem.repository.CategoryRepo;
import quanaotreem.service.CategoryServ;

@Service
public class CategoryServImpl implements CategoryServ {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	@Transactional
	public Category findById(Long id) {
		Optional<Category> optional = categoryRepo.findById(id);
		return optional.orElse(null);
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
		return categoryRepo.save(category);
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

	@Override
	@Transactional
	public List<Category> findAllByNameLike(String name) {
		return categoryRepo.findAllByNameLike("%" + name + "%");
	}

	@Override
	@Transactional
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

}
