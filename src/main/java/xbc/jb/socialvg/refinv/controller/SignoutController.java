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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

/**
 *
 */
@Controller
@RequestMapping("/signout")
public class SignoutController {

	private Logger logger = LoggerFactory.getLogger(SignoutController.class);

	@Autowired
	private UserSecurityService userSecurityService;

	@GetMapping
	public String showSigninForm(Model model)
	{
		Optional<User> opUser = userSecurityService.getAuthenticatedUser();
		if (!opUser.isPresent())
			return "redirect:/";
		//model.addAttribute("user", new User());
		return "/signout";
	}

	@PostMapping
	public String signin(HttpServletRequest httpServletRequest) {
		logger.info(String.format("Signing out ..."));
		httpServletRequest.getSession().invalidate();
		return "redirect:/signin";
	}
}