package xbc.jb.socialvg.refinv.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xbc.jb.socialvg.refinv.controller.UploadController;
import xbc.jb.socialvg.refinv.domain.Image;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.properties.WebappProperties;
import xbc.jb.socialvg.refinv.repository.UserPageRepository;
import xbc.jb.socialvg.refinv.repository.UserRepository;

import javax.jws.soap.SOAPBinding;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 */
@Service
public class UserServiceDb implements UserService{

	private Logger logger = LoggerFactory.getLogger(UserServiceDb.class);

    private UserRepository userRepository;
    private UserPageRepository userPageRepository;
    private PasswordEncoder passwordEncoder;
    private WebappProperties webappProperties;
    private ImageServiceDb imageServiceDb;

    @Autowired
	public UserServiceDb(UserRepository userRepository, UserPageRepository userPageRepository, PasswordEncoder passwordEncoder, WebappProperties webappProperties, ImageServiceDb imageServiceDb) {
		this.userRepository = userRepository;
		this.userPageRepository = userPageRepository;
		this.passwordEncoder = passwordEncoder;
		this.webappProperties = webappProperties;
		this.imageServiceDb = imageServiceDb;
	}

	@Override
    public void save(User user)
    {
    	Optional<User> opRUser = userRepository.findUserByRCode(user.getiCode());
    	user.setScore(.0);
    	user.setInvitees(0L);
    	user.setDirect(0L);
//    	user.setInvitedUsers(new HashSet<>());
    	if (opRUser.isPresent())
			updateScore(user, 1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        generateRCode(user);
        userRepository.save(user);
    }

    @Override
    public void delete(User user)
    {
        userRepository.delete(user);
    }

    @Override
    public void update(User user)
    {
        userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username)
    {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> opUser = userRepository.findUserByUsername(username);
        if (opUser.isPresent())
            return opUser.get();
        else
            throw new UsernameNotFoundException(username);
    }

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> findPage(Pageable pageRequest) {

		Page<User> page = userPageRepository.findAll(pageRequest);
		return page;
	}

	@Override
	public Page<User> findPageSafe(Pageable pageRequest) {

		Page<User> page = userPageRepository.findAll(pageRequest);
		for (User user: page)
			user.setPassword("");
		return page;
	}

	@Override
	public void updateScore(User invitedUser, int lev) {

		if (invitedUser == null)
			return;
		if (invitedUser.getiCode() == null)
			return;
		Optional<User> opUser = userRepository.findUserByRCode(invitedUser.getiCode());
		if (!opUser.isPresent())
			return;
		//opUser.get().addScore(Math.exp(-l));
		opUser.get().addInvitee(1L);
		if (lev == 1)
			opUser.get().addDirect(1L);
		opUser.get().addScore(1.0 / lev);
//		user.getInvitedUsers().add(invitedUser);
		update(opUser.get());
		updateScore(opUser.get(), lev +  1);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public long countAllByRCode(String rCode) {
		return userRepository.countAllByRCode(rCode);
	}

	@Override
	public void generateRCode(User user) {

		if (user == null || user.getUsername() == null)
			return;
		StringBuilder sb = new StringBuilder();
		sb.append(user.getUsername().substring(0, 4).toUpperCase());
		sb.append('-');
		while (true)
		{
			Random random = new Random();
			Integer rNum = random.nextInt(1000000);
			sb.append(String.format("%06d", rNum));
			if (userRepository.findUserByRCode(sb.toString()).isPresent())
				sb.delete(5, sb.toString().length());
			else
			{
				user.setrCode(sb.toString());
				break;
			}
		}
	}

	@Override
	public Page<User> findAllByRCodeAnd(String rCode, Pageable page) {
		return userPageRepository.findAllByRCode(rCode, page);
	}

	@Override
	public Page<User> pageHidePassword(Page<User> page) {
		if (page == null)
			return page;
		page.forEach(user -> user.setPassword(""));
		return page;
	}

	@Override
	public void uploadImage(User user, MultipartFile multipartFile) {
		String folder = webappProperties.getPath().getImageFolder()
				+ user.getUsername();
		try {
			byte[] bytes = multipartFile.getBytes();
			Files.createDirectories(Paths.get(folder));
			Path fullPath = Paths.get(folder
					+ webappProperties.getPath().getFileSep()
					+ multipartFile.getOriginalFilename());
			logger.info(String.format("fullpath: %s", fullPath));
			Files.write(fullPath, bytes);
			Image image = new Image();
			image.setPath(fullPath.toString());
			image.setProfile(true);
			imageServiceDb.save(image);
			user.getImages().add(image);
			update(user);
		}
		catch (Exception e)
		{
			logger.info("Failed to upload !");
			e.printStackTrace();
		}
	}
}
