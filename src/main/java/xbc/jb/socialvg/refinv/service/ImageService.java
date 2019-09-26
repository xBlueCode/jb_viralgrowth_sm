package xbc.jb.socialvg.refinv.service;

import org.springframework.stereotype.Service;
import xbc.jb.socialvg.refinv.domain.Image;
import xbc.jb.socialvg.refinv.domain.User;

@Service
public interface ImageService {

	void save(Image image);
	void delete(Image image);
	void update(Image image);
}
