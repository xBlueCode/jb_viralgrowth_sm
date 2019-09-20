package xbc.jb.socialvg.refinv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import java.util.Optional;

/**
 *
 */
@Component
public class UserSecurityUtil {

    private UserServiceDb userServiceDb;

    @Autowired @Lazy
    public UserSecurityUtil(UserServiceDb userServiceDb) {
        this.userServiceDb = userServiceDb;
    }

    public Optional<User> getAuthenticatedUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated())
            return Optional.empty();
        else
            return userServiceDb.findUserByUsername(auth.getName());
    }
}
