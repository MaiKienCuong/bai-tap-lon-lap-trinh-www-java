package maikiencuong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maikiencuong.entity.Orderr;

@Repository
public interface OrderRepo extends JpaRepository<Orderr, Long> {

	public Page<Orderr> findAllByCustomer_Id(Long id, Pageable pageable);
	
}
