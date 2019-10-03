package xbc.jb.socialvg.refinv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xbc.jb.socialvg.refinv.config.UserSecurityService;
import xbc.jb.socialvg.refinv.domain.User;
import xbc.jb.socialvg.refinv.service.UserServiceDb;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Signing-up form Controller.
 */
@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserServiceDb userServiceDb;

    @Autowired
    private UserSecurityService userSecurityService;

    @GetMapping
    public String showSignupForm(Model model)
    {
        Optional<User> opUser = userSecurityService.getAuthenticatedUser();
        if (opUser.isPresent())
            return "redirect:/dashboard";
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute("user") @Valid User validUuser, BindingResult bindingResult)
    {
    	try {
			if (userServiceDb.findUserByUsername(validUuser.getUsername()).isPresent())
				bindingResult.rejectValue(
						"username",
						null,
						"There is already an account with that username !");
			if (bindingResult.hasErrors())
				return "signup";

//			User user = new User();
//			user.setUsername(validUuser.getUsername());
//			user.setPassword(validUuser.getPassword());
//			user.setEmail(validUuser.getEmail());
//			user.setrCode(validUuser.getrCode());
			System.out.format("rCode: %s\n", validUuser.getrCode());
			userServiceDb.save(validUuser);
			return "redirect:/signin";
		}
        catch (Exception e)
		{
			e.printStackTrace();
			return "signup";
		}
    }
}
