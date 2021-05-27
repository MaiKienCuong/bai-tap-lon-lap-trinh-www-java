package quanaotreem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import quanaotreem.entity.Orderr;
import quanaotreem.enumvalue.EnumStatusOrder;

public interface OrderServ {

	public Orderr findById(Long id);

	public Orderr add(Orderr order);

	public Orderr update(Orderr order);

	public void delete(Long id);

	public Page<Orderr> findAll(Pageable pageable);
	
	public List<Orderr> findAllByCustomer_Id(Long id);

	public Page<Orderr> findAllByCustomer_Id(Long id, Pageable pageable);
	
	public Page<Orderr> findAllByStatusIn(EnumStatusOrder[] status, Pageable pageable);

}
