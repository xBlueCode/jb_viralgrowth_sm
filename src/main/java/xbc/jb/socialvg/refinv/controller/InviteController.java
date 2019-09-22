package xbc.jb.socialvg.refinv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.domain.User;

import java.util.Optional;

@Controller
@RequestMapping("/invite")
public class InviteController {

	private Logger logger = LoggerFactory.getLogger(SigninController.class);

	@Autowired
	private UserSecurityService userSecurityService;

	@GetMapping
	public String showSigninForm(@RequestParam("code") String icode, Model model)
	{
		Optional<User> opUser = userSecurityService.getAuthenticatedUser();
		if (opUser.isPresent())
			return "redirect:/dashboard";
		User user = new User();
		user.setiCode(icode);
		model.addAttribute("user", user);
		return "signup";
	}

/*	@PostMapping
	public String signin(@ModelAttribute("user") User user, BindingResult bindingResult) {
		System.out.println("---------------------------+++++");
		logger.info(String.format("Signing in ...: %s", user.getUsername()));
		if (userSecurityService.authenticate(user))
			return "redirect:/dashboard";
		else
			return "signin";
	}*/
}
