package quanaotreem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quanaotreem.entity.Image;
import quanaotreem.repository.ImageRepo;
import quanaotreem.service.ImageServ;

@Service
public class ImageServImpl implements ImageServ {

	@Autowired
	private ImageRepo imageRepo;

	@Override
	@Transactional
	public void deleteById(Long id) {
		imageRepo.deleteById(id);
	}

	@Override
	@Transactional
	public List<Image> findAllByProduct_Id(Long id) {
		return imageRepo.findAllByProduct_Id(id);
	}

}
