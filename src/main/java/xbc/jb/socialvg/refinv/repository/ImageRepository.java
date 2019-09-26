package xbc.jb.socialvg.refinv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xbc.jb.socialvg.refinv.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
