package xbc.jb.socialvg.refinv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xbc.jb.socialvg.refinv.config.UserSecurityService;

@Controller
@RequestMapping("/list")
public class ListController {

	@Autowired
	private UserSecurityService userSecurityService;

	@GetMapping
	public String dashboard()
	{
		/*
		Optional<User> opUser = userSecurityUtil.getAuthenticatedUser();
		if (opUser.isPresent())
			return "dashboard";
		else
			return "redirect:/";
		 */
		return "list";
	}
}
