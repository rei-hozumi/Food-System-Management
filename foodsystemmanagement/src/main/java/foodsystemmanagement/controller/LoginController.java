package foodsystemmanagement.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import foodsystemmanagement.entity.User;
import foodsystemmanagement.service.UserService;

@Controller
public class LoginController {
	private final UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	@PostMapping("/login")
	public String login(@RequestParam String loginId,
						@RequestParam String password,
						HttpSession session,
						Model model) {
		User user = userService.login(loginId,password);
        if(user != null){
            session.setAttribute("loginUser", user);
            return "redirect:/top";
        }
        model.addAttribute("error","ログインIDまたはパスワードが違います");
        return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
