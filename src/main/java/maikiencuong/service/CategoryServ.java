package maikiencuong.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import maikiencuong.entity.Category;

public interface CategoryServ {

	public Category findById(Long id);

	public Page<Category> findAll(Pageable pageable);
	
	public List<Category> findAll();

	public List<Category> findAllByNameLike(String name);

	public Page<Category> findAllByNameLike(String name, Pageable pageable);

	public Category add(Category category);

	public void delete(Long id);

	public Category update(Category category);

}
