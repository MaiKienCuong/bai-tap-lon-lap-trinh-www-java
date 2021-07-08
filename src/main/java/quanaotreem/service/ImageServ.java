package quanaotreem.service;

import java.util.List;

import quanaotreem.entity.Image;

public interface ImageServ {

	void deleteById(Long id);
	
	List<Image> findAllByProduct_Id(Long id);

}
