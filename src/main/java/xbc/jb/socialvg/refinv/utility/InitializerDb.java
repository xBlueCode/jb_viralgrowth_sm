package xbc.jb.socialvg.refinv.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

@Component
public class InitializerDb implements CommandLineRunner {

	@Autowired
	private UserServiceDb userServiceDb;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++)
		{
			User user = new User();
			user.setUsername(String.format("user%d", i));
			user.setPassword(String.format("pass%d", i));
			user.setrCode(String.format("refcode%d", i));
			user.setiCode(String.format("refcode%d", i - 1));
			user.setEmail(String.format("user%d@gmail.com", i));
			userServiceDb.save(user);
		}
	}
}
