package quanaotreem.service;

import java.util.List;

import quanaotreem.entity.Image;

public interface ImageServ {

	public void deleteById(Long id);
	
	public List<Image> findAllByProduct_Id(Long id);

}
