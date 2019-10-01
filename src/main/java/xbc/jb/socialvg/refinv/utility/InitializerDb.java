package xbc.jb.socialvg.refinv.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import xbc.jb.socialvg.refinv.domain.Photo;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.service.PhotoServiceDb;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

/**
 * WebApp Initilizer: Creates some users upon the start of the application.
 */
@Component
public class InitializerDb implements CommandLineRunner {


	private UserServiceDb userServiceDb;

	private PhotoServiceDb photoServiceDb;

	@Autowired
	public InitializerDb(@Lazy UserServiceDb userServiceDb, PhotoServiceDb photoServiceDb) {
		this.userServiceDb = userServiceDb;
		this.photoServiceDb = photoServiceDb;
	}

	@Override
	public void run(String... args) throws Exception {

		String lastRcode = new String("");
		for (int i = 0; i < 20; i++)
		{
			User user = new User();
			user.setUsername(String.format("us%02d", i));
			user.setPassword(String.format("pass%d", i));
			if (i > 0)
				user.setiCode(lastRcode);
			user.setEmail(String.format("user%d@gmail.com", i));
//			user.setInvitedUsers(Collections.EMPTY_SET);
			userServiceDb.save(user);
			if (i == 10)
				System.out.println(lastRcode);
			lastRcode = user.getrCode();
		}
		User user1 = userServiceDb.findUserByUsername("us01").get();
		user1.getInvited().add(userServiceDb.findUserByUsername("us03").get());
		userServiceDb.update(user1);
	}
}
