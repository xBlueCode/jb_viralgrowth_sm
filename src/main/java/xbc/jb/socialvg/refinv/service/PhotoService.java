package xbc.jb.socialvg.refinv.service;

import org.springframework.stereotype.Service;
import xbc.jb.socialvg.refinv.domain.Photo;

@Service
public interface PhotoService {

	void save(Photo photo);
	void delete(Photo photo);
	void update(Photo photo);
	Photo newDefault();
}
