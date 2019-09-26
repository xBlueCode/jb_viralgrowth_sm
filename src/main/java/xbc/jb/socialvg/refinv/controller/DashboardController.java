package xbc.jb.socialvg.refinv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.domain.User;

import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private UserSecurityService userSecurityService;

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
