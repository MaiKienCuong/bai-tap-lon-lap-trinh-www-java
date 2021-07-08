package quanaotreem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import quanaotreem.entity.Category;

public interface CategoryServ {

	Category findById(Long id);

	Page<Category> findAll(Pageable pageable);
	
	List<Category> findAll();

	List<Category> findAllByNameLike(String name);

	Page<Category> findAllByNameLike(String name, Pageable pageable);

	Category add(Category category);

	void delete(Long id);

	Category update(Category category);

}
