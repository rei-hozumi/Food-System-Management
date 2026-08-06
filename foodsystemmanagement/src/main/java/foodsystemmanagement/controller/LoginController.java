package foodsystemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import foodsystemmanagement.entity.User;


@Controller
public class LoginController {
	@GetMapping("/login")
	public String loginForm(Model model) {
		if(!model.containsAttribute("logionForm")) {
			model.addAttribute("loginForm",new User());
		}
		return "login";
	}
}
