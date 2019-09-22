package xbc.jb.socialvg.refinv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

@Controller
@RequestMapping("/list")
public class ListController {

	@Autowired
	private UserSecurityService userSecurityService;

	@Autowired
	private UserServiceDb userServiceDb;

	@GetMapping
	public String dashboard(Model model)
	{
		/*
		Optional<User> opUser = userSecurityUtil.getAuthenticatedUser();
		if (opUser.isPresent())
			return "dashboard";
		else
			return "redirect:/";
		 */
		model.addAttribute("list", userServiceDb.findAll());
		return "list";
	}
}
