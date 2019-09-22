package xbc.jb.socialvg.refinv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xbc.jb.socialvg.refinv.domain.User;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *
     * @param username
     * @return
     */
    Optional<User> findUserByUsername(String username);

    List<User> findAll();
}
