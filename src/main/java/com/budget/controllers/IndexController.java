package com.budget.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.budget.dto.ChartData;
import com.budget.dto.Datasets;
import com.budget.model.User;
import com.budget.services.UserService;

@Controller
public class IndexController {
	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping("/")
	public String index(Model model) {
		double[] data = { 10.3, 10.3, 40.3, 40.3 };
		String[] colours = { "#FF6384", "#36A2EB", "#FFCE56", "#36A2EB" };
		String[] labels = { "lable10", "lable1010", "lable40", "lable4040" };
		Map<String, Object> pieData = new HashMap<String, Object>();
		pieData.put("data", data);
		pieData.put("labels", labels);
		// model.addAttribute("javaPieData2", pieData);

		Datasets datasets = new Datasets();
		datasets.setData(data);
		datasets.setBackgroundColor(colours);

		ChartData chartData = new ChartData();
		chartData.setDatasets(new Datasets[] { datasets });
		chartData.setLabels(labels);
		model.addAttribute("javaPieData2", chartData);

		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "pages-login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
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
