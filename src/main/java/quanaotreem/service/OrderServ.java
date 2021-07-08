package quanaotreem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import quanaotreem.entity.Orderr;
import quanaotreem.enumvalue.EnumStatusOrder;

public interface OrderServ {

	Orderr findById(Long id);

	Orderr add(Orderr order);

	Orderr update(Orderr order);

	void delete(Long id);

	Page<Orderr> findAll(Pageable pageable);
	
	List<Orderr> findAllByCustomer_Id(Long id);

	Page<Orderr> findAllByCustomer_Id(Long id, Pageable pageable);
	
	Page<Orderr> findAllByStatusIn(EnumStatusOrder[] status, Pageable pageable);

}
