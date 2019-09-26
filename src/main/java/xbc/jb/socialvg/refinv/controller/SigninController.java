package xbc.jb.socialvg.refinv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.domain.User;

import java.util.Optional;

/**
 * Signing-in form Controller.
 */
@Controller
@RequestMapping("/signin")
public class SigninController {

	private Logger logger = LoggerFactory.getLogger(SigninController.class);

	@Autowired
	private UserSecurityService userSecurityService;

	@GetMapping
	public String showSigninForm(Model model)
	{
		Optional<User> opUser = userSecurityService.getAuthenticatedUser();
		if (opUser.isPresent())
			return "redirect:/dashboard";
		model.addAttribute("user", new User());
		return "/signin";
	}

	@PostMapping
	public String signin(@ModelAttribute("user") User user, BindingResult bindingResult) {
		System.out.println("---------------------------+++++");
		logger.info(String.format("Signing in ...: %s", user.getUsername()));
		try {
			if (userSecurityService.authenticate(user))
				return "redirect:/dashboard";
		}
		catch (Exception e) { }
		logger.info(String.format("Signing in failed: %s", user.getUsername()));
		bindingResult.reject(
				"badcred",
				"You've entered an incorrect username and/or password. Please try again !");
		return "signin";
	}
}
