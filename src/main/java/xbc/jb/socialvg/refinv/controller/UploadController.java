package xbc.jb.socialvg.refinv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import java.util.Optional;

/**
 * Uploading Controller.
 * Accepts multipart-file as request-param and save the picture in the file system
 * besides mapping it to its owner in the DB.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

	private Logger logger = LoggerFactory.getLogger(UploadController.class);

	private UserServiceDb userServiceDb;
	private UserSecurityService userSecurityService;

	@Autowired
	public UploadController(UserServiceDb userServiceDb, UserSecurityService userSecurityService) {
		this.userServiceDb = userServiceDb;
		this.userSecurityService = userSecurityService;
	}

	@PostMapping
	public String uploadImage(@RequestParam("file")MultipartFile multipartFile)
	{
		Optional<User> opUser = userSecurityService.getAuthenticatedUser();
		logger.info("Authenticated !");
		if (opUser.isPresent())
		{
			logger.info("Calling service upload !");
			try {
				userServiceDb.uploadImage(opUser.get(), multipartFile);
			}
			catch (Exception e)
			{
			}
		}
		return "redirect:/dashboard";
	}
}
