package com.budget.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.budget.model.User;
import com.budget.services.UserService;

@Controller
public class IndexController {
	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "pages-login";
	}

	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "pages-register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value = "user") User user, BindingResult result) {
		if (!userService.addNewUser(user)) {
			result.addError(new ObjectError("password",
					messageSource.getMessage("budget.register.error.password", new Object[] {}, null)));
			return "pages-register";
		}
		return "pages-login";
	}
}
