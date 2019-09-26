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

	/**
	 * Count all the users.
	 * @return The count of all users.
	 */
	long count();


	/**
	 * Count all users who are invited by the same iCode.
	 * @param iCode The invitation code.
	 * @return The count of users who have the same iCode.
	 */
	long countAllByICode(String iCode);

	/**
	 * Generate a new Referral Code.
	 * @param user The targeted user.
	 */
	void generateRCode(User user);

	/**
	 * Save the file in the file-system and map it to the user(owner) in the DB.
	 * @param user
	 * @param multipartFile
	 */
	void uploadImage(User user, MultipartFile multipartFile);
}
