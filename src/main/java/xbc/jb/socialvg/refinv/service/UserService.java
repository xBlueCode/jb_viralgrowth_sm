package xbc.jb.socialvg.refinv.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xbc.jb.socialvg.refinv.domain.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {

    void save(User user);
    void delete(User user);
    void update(User user);
	Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);

    List<User> findAll();
	Page<User> findPage(Pageable pageRequest);
	Page<User> findPageSafe(Pageable pageRequest);
	Page<User> findAllByRCode(String rCode, Pageable page);
	Page<User> findAllByICode(String iCode, Pageable page);

	Page<User> pageHidePassword(Page<User> page);

	void updateScore(User invitedUser, int lev);

	long count();

	long countAllByRCode(String rCode);
	long countAllByICode(String iCode);

	void generateRCode(User user);

	void uploadImage(User user, MultipartFile multipartFile);
}
