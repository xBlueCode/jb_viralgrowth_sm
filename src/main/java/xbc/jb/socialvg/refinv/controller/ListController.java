package xbc.jb.socialvg.refinv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.properties.WebappProperties;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * List Controller
 * Provides 3 kinds of listing: All, All Invited and Directly Invited.
 */
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

	/**
	 * Handles a list request, accepts `type` and `page` as parameters to serve the appropriate data.
	 * `page`:: page index;
	 * `type`:: {0: all, 1: all-invited, 2: directly-invited}
	 * @param model The model provided to the list template.
	 * @param type The type of the list.
	 * @param pageN The index of the requested page.
	 * @return
	 */
	@GetMapping
	public String list(Model model, @RequestParam("type") int type, @RequestParam("page") int pageN)
	{
		Optional<User> opUser = userSecurityService.getAuthenticatedUser();
		if (!opUser.isPresent())
			return "redirect:/";
		opUser.get().setPassword("");
		model.addAttribute("currentUser", opUser.get());
		try{
			int pageSize = webappProperties.getPagination().getPageSize();
			Page<User> usersPage = null;
			if (type == 0)
			{
				usersPage = userServiceDb.findPage(PageRequest.of(pageN - 1, pageSize));
				model.addAttribute("title", "All users");
			}
			else if (type == 1)
			{
				List<User> invitedUsers = opUser.get().getInvited();
				usersPage = new PageImpl<>(invitedUsers);
				model.addAttribute("title", "All invitees");
			}
			else if (type == 2)
			{
				usersPage = userServiceDb.findAllByICode(
						opUser.get().getrCode(),
						PageRequest.of(pageN - 1, pageSize));
				model.addAttribute("title", "Direct invitees");
			}
			else if (type == 3)
			{
				usersPage = userServiceDb.findAllByRCode(opUser.get().getiCode(),
						PageRequest.of(pageN - 1, pageSize));
				model.addAttribute("title", "Direct referrer");
			}
			else if (type == 4)
			{
				List<User> referrers = opUser.get().getReferrers();
				usersPage = new PageImpl<>(referrers);
				model.addAttribute("title", "All referrers");
			}
			if (usersPage == null)
				throw new NullPointerException();
			long pageMax = usersPage.getTotalPages();
			model.addAttribute("list", userServiceDb.pageHidePassword(usersPage));
			model.addAttribute("pageMax", pageMax);
			model.addAttribute("type", type);
		}
		catch (Exception e) {
			model.addAttribute("list", Collections.EMPTY_LIST);
			model.addAttribute("pageMax", 1);
			model.addAttribute("type", type);
			return "list";
		}
		return "list";
	}
}
