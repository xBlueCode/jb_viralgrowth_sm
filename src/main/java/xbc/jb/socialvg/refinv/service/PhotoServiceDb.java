package xbc.jb.socialvg.refinv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xbc.jb.socialvg.refinv.domain.Photo;
import xbc.jb.socialvg.refinv.properties.WebappProperties;
import xbc.jb.socialvg.refinv.repository.PhotoRepository;

@Service
public class PhotoServiceDb implements PhotoService {

	private PhotoRepository photoRepository;
	private WebappProperties webappProperties;

	@Autowired
	public PhotoServiceDb(PhotoRepository photoRepository, WebappProperties webappProperties) {
		this.photoRepository = photoRepository;
		this.webappProperties = webappProperties;
	}

	@Override
	public Photo save(Photo photo) {
		if (photo == null)
			return null;
		return photoRepository.save(photo);
	}

	@Override
	public void delete(Photo photo) {
		photoRepository.delete(photo);
	}

	@Override
	public void update(Photo photo) {
		photoRepository.saveAndFlush(photo);
	}

	@Override
	public Photo newDefault() {
		Photo photo = new Photo();
		photo.setProfile(true);
		photo.setPath(webappProperties.getPath().getProfileDefImg());
//		System.out.println(photo.getPath());
		return save(photo);
		//return (photo);
	}
}
