package xbc.jb.socialvg.refinv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xbc.jb.socialvg.refinv.config.UserSecurityUtil;
import xbc.jb.socialvg.refinv.domain.User;

import java.util.Optional;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserSecurityUtil userSecurityUtil;

	@GetMapping("/grids")
	public String grids()
	{
		return "grids";
	}
}
