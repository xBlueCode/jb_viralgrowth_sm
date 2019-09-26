package xbc.jb.socialvg.refinv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import xbc.jb.socialvg.refinv.domain.User;

public interface UserPageRepository extends PagingAndSortingRepository<User, Long> {

	Page<User>	findAll(Pageable pageable);

	Page<User>	findAllByRCode(String rCode, Pageable page);
}
