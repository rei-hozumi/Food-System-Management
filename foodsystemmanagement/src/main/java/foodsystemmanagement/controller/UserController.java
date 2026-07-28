package foodsystemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import foodsystemmanagement.entity.User;
import foodsystemmanagement.service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@GetMapping("/users/register")
	public String registerForm(Model model) {
		model.addAttribute("user",new User());
		return "users/register";
	}
	@PostMapping("/users/register")
	public String register(@ModelAttribute User user) {
		userService.save(user);
		return "redirect:/login";
	}
}
