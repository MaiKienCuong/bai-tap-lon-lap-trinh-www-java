package maikiencuong.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

	public Page<Category> findAllByNameLike(String name, Pageable pageable);

	public List<Category> findAllByNameLike(String name);

}
