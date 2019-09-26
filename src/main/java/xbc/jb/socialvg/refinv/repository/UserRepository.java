package xbc.jb.socialvg.refinv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xbc.jb.socialvg.refinv.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByRCode(String rCode);

    long countAllByRCode(String rCode);
    long countAllByICode(String iCode);

    List<User> findAll();

    long count();
}
