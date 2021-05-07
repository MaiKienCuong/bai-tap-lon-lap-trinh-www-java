package maikiencuong.service;

import maikiencuong.entity.Image;

public interface ImageServ {

	public void deleteById(Long id);
	
	public void update(Image image);

}
