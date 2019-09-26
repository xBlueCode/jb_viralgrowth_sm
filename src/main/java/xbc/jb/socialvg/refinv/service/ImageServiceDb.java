package xbc.jb.socialvg.refinv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xbc.jb.socialvg.refinv.domain.Image;
import xbc.jb.socialvg.refinv.repository.ImageRepository;

@Service
public class ImageServiceDb implements ImageService{

	private ImageRepository imageRepository;

	@Autowired
	public ImageServiceDb(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@Override
	public void save(Image image) {
		imageRepository.save(image);
	}

	@Override
	public void delete(Image image) {
		imageRepository.delete(image);
	}

	@Override
	public void update(Image image) {
		imageRepository.saveAndFlush(image);
	}
}
