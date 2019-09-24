package xbc.jb.socialvg.refinv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.repository.UserPageRepository;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/list")
public class ListController {

	@Autowired
	private UserSecurityService userSecurityService;

	@Autowired
	private UserServiceDb userServiceDb;

	@GetMapping
	public String dashboard(Model model, @RequestParam("page") int pageN)
	{
		/*
		Optional<User> opUser = userSecurityUtil.getAuthenticatedUser();
		if (opUser.isPresent())
			return "dashboard";
		else
			return "redirect:/";
		 */
//		model.addAttribute("list", userServiceDb.findAll());

		long count = userServiceDb.count();
		long pageMax = count / 4 + ((count % 4) == 0 ? 0 : 1);
		model.addAttribute("list", userServiceDb.findPage(PageRequest.of(pageN - 1, 4)));
		model.addAttribute("pageMax", pageMax);
		return "list";
	}
}
