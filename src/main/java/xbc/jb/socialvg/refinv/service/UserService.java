package xbc.jb.socialvg.refinv.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import xbc.jb.socialvg.refinv.domain.User;

import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {

    void save(User user);
    void delete(User user);
    void update(User user);
    Optional<User> findUserByUsername(String username);
}
