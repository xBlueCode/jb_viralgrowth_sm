package xbc.jb.socialvg.refinv.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import java.util.Optional;

/**
 *
 */
@Component
public class UserSecurityService {

    private UserServiceDb userServiceDb;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    //private DaoAuthenticationProvider daoAuthenticationProvider;

    private static final Logger logger = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired @Lazy
    public UserSecurityService(UserServiceDb userServiceDb, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userServiceDb = userServiceDb;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getAuthenticatedUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated())
            return Optional.empty();
        else
            return userServiceDb.findUserByUsername(auth.getName());
    }

    public boolean authenticate(UserDetails user)
    {
        UserDetails userDetails = userServiceDb.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(
                        userDetails, user.getPassword(), user.getAuthorities());
        logger.info(String.format("Authenticating : %s | %s", user.getUsername(), user.getPassword()));
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated())
        {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.info(String.format("Authentication successed : %s", user.getUsername()));
            return true;
        }
        logger.info(String.format("Authentication failed : %s", user.getUsername()));
        return false;
    }
}
