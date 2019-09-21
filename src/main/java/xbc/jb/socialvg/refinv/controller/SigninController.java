package xbc.jb.socialvg.refinv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xbc.jb.socialvg.refinv.config.UserSecurityUtil;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import javax.validation.Valid;
import java.util.Optional;

/**
 *
 */
@Controller
@RequestMapping("/signin")
public class SigninController {

	@Autowired
	private UserSecurityUtil userSecurityUtil;

	@GetMapping
	public String showSigninForm(Model model)
	{
		Optional<User> opUser = userSecurityUtil.getAuthenticatedUser();
		if (opUser.isPresent())
			return "redirect:/dashboard";
		model.addAttribute("user", new User());
		return "signin";
	}

	@PostMapping
	public String signin()
	{
		Optional<User> opUser = userSecurityUtil.getAuthenticatedUser();
		if (opUser.isPresent())
			return "redirect:/dashboard";
		else
			return "signin";
	}
}
