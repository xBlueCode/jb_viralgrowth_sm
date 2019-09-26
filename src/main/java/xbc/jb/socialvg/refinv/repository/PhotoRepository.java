package xbc.jb.socialvg.refinv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xbc.jb.socialvg.refinv.domain.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

	Photo save(Photo photo);
}
