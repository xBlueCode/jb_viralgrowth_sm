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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
	public String dashboard(Model model, @RequestParam("type") String type, @RequestParam("page") int pageN)
	{
		Optional<User> opUser = userSecurityService.getAuthenticatedUser();
		if (!opUser.isPresent())
			return "redirect:/";
		opUser.get().setPassword("");
		model.addAttribute("currentUser", opUser.get());
		try{
			int pageSize = webappProperties.getPaginationProperties().getPageSize();
			long count = userServiceDb.count();
			long pageMax = count / pageSize + ((count % pageSize) == 0 ? 0 : 1);
			model.addAttribute("list", userServiceDb.findPageSafe(PageRequest.of(pageN - 1, pageSize)));
			model.addAttribute("pageMax", pageMax);
		}
		catch (Exception e) {
			model.addAttribute("list", Collections.EMPTY_LIST);
			model.addAttribute("pageMax", 1);
			return "list";
		}
		return "list";
	}
}
