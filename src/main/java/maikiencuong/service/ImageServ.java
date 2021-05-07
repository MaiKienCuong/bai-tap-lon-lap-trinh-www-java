package maikiencuong.service;

import java.util.List;

import maikiencuong.entity.Image;

public interface ImageServ {

	public void deleteById(Long id);
	
	public List<Image> findAllByProduct_Id(Long id);

}
