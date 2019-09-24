package xbc.jb.socialvg.refinv.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import xbc.jb.socialvg.refinv.domain.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {

    void save(User user);
    void delete(User user);
    void update(User user);
    Optional<User> findUserByUsername(String username);

    List<User> findAll();
	Page<User> findPage(Pageable pageRequest);

	void updateScore(String rCode, int l);

	long count();
}
