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
import xbc.jb.socialvg.refinv.properties.WebappProperties;
import xbc.jb.socialvg.refinv.repository.UserPageRepository;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/list")
public class ListController {

	private UserSecurityService userSecurityService;
	private UserServiceDb userServiceDb;
	private WebappProperties webappProperties;

	@Autowired
	public ListController(UserSecurityService userSecurityService, UserServiceDb userServiceDb, WebappProperties webappProperties) {
		this.userSecurityService = userSecurityService;
		this.userServiceDb = userServiceDb;
		this.webappProperties = webappProperties;
	}

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

		try{
			int pageSize = webappProperties.getPaginationProperties().getPageSize();
			System.out.format("Page Size:  %d\n", pageSize);
			long count = userServiceDb.count();
			long pageMax = count / pageSize + ((count % pageSize) == 0 ? 0 : 1);
			model.addAttribute("list", userServiceDb.findPage(PageRequest.of(pageN - 1, pageSize)));
			model.addAttribute("pageMax", pageMax);
		}
		catch (Exception e) { }
		return "list";
	}
}
