package xbc.jb.socialvg.refinv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xbc.jb.socialvg.refinv.config.UserSecurityService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserSecurityService userSecurityService;

	@GetMapping("/grids")
	public String grids()
	{
		return "grids";
	}
}
