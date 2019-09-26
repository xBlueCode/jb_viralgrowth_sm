package xbc.jb.socialvg.refinv.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xbc.jb.socialvg.refinv.domain.Image;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.service.ImageServiceDb;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Component
public class InitializerDb implements CommandLineRunner {

	@Autowired
	private UserServiceDb userServiceDb;

	@Autowired
	private ImageServiceDb imageServiceDb;

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
			/*Image image = imageServiceDb.newDefault();
			user = userServiceDb.findUserByUsername(user.getUsername()).get();
			user.getImages().add(image);
			userServiceDb.update(user);*/
			if (i == 10)
				System.out.println(lastRcode);
			lastRcode = user.getrCode();
		}
	}
}
