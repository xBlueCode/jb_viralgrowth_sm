package xbc.jb.socialvg.refinv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import java.util.Optional;


/**
 * Dashboard Controller - (Main user profile page)
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private UserSecurityService userSecurityService;
	@Autowired
	private UserServiceDb userServiceDb;

	/**
	 * Provide `dashboard` view with the model `currentUser` which is the current authenticated user, or redirect to the
	 * main page otherwise.
	 * @param model
	 * @return
	 */
	@GetMapping
	public String dashboard(Model model)
	{
		Optional<User> opUser = userSecurityService.getAuthenticatedUser();
		if (opUser.isPresent())
		{
			model.addAttribute("currentUser", opUser.get());
			return "dashboard";
		}
		else
			return "redirect:/";
	}
}
