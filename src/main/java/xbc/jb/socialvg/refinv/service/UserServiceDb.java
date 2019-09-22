package xbc.jb.socialvg.refinv.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.repository.UserPageRepository;
import xbc.jb.socialvg.refinv.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class UserServiceDb implements UserService{

    private UserRepository userRepository;
    private UserPageRepository userPageRepository;
    private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceDb(UserRepository userRepository, UserPageRepository userPageRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userPageRepository = userPageRepository;
		this.passwordEncoder = passwordEncoder;
	}


    @Override
    public void save(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
		return userPageRepository.findAll(pageRequest);
	}
}
