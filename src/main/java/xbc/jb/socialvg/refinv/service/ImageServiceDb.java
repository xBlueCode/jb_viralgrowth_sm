package xbc.jb.socialvg.refinv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xbc.jb.socialvg.refinv.domain.Image;
import xbc.jb.socialvg.refinv.properties.WebappProperties;
import xbc.jb.socialvg.refinv.repository.ImageRepository;

@Service
public class ImageServiceDb implements ImageService{

	private ImageRepository imageRepository;
	private WebappProperties webappProperties;

	@Autowired
	public ImageServiceDb(ImageRepository imageRepository, WebappProperties webappProperties) {
		this.imageRepository = imageRepository;
		this.webappProperties = webappProperties;
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

	@Override
	public Image newDefault() {
		Image image = new Image();
		image.setProfile(true);
		image.setPath(webappProperties.getPath().getProfileDefImg());
		save(image);
		return (image);
	}
}
