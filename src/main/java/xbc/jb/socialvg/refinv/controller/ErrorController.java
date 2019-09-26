package xbc.jb.socialvg.refinv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Error Controller
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

	/**
	 * Return the `error` view which provide a redirection to the appropriate location after an error occurs.
	 * @return
	 */
	@GetMapping
	public String error()
	{
		return "error";
	}
}
