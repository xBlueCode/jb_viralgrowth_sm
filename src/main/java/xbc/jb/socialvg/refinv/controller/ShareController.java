package xbc.jb.socialvg.refinv.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.domain.User;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Optional;

/*@Controller
@RequestMapping("/share")*/
public class ShareController {
/*
	private Logger logger = LoggerFactory.getLogger(ShareController.class);

	@Autowired
	private UserSecurityService userSecurityService;

	@PostMapping
	public String share(Model model)
	{
		Optional<User> opUser = userSecurityService.getAuthenticatedUser();

		if (!opUser.isPresent())
			return "redirect:/";
		StringBuilder linkb = new StringBuilder("http://localhost:8080/invite?code=");
		linkb.append(opUser.get().getrCode());

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(linkb.toString());
		clipboard.setContents(strSel, null);

		model.addAttribute("copy_msg", "Link Copied !");
		return "dashboard";
	}*/
}
