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
 *
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
    public String signup(@ModelAttribute("user") @Valid User user, BindingResult bindingResult)
    {
        if (userServiceDb.findUserByUsername(user.getUsername()).isPresent())
            bindingResult.rejectValue(
                    "username",
                    null,
                    "There is already an account with that username !");
		if (bindingResult.hasErrors())
			return "signup";
		userServiceDb.save(user);
        System.out.println("saved ++++++++++++++++++++++++");
		return "redirect:/signin";
    }
}
