package foodsystemmanagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import foodsystemmanagement.entity.User;
import foodsystemmanagement.service.UserService;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	//一覧
	@GetMapping("/list")
	public String list(@RequestParam(required =false)String keyword,Model model) {
		model.addAttribute("userList",userService.search(keyword));
		model.addAttribute("keyword",keyword);
		return "users/list";
	}
	//登録
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user",new User());
		return "users/register";
	}
	@PostMapping("/register")
	public String register(@ModelAttribute User user) {
		userService.save(user);
		return "redirect:/users/list";
	}
	//編集
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id,Model model) {
		model.addAttribute("user",userService.findById(id));
		return "users/edit";
	}
	@PostMapping("/update")
	public String update(User user,RedirectAttributes redirectAttributes) {
		userService.update(user);
		redirectAttributes.addFlashAttribute("message","ユーザーを編集しました。");
		return "redirect:/users/list";
	}
	//削除
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes) {
		userService.delete(id);
		redirectAttributes.addFlashAttribute("message","ユーザーを削除しました。");
		return "redirect:/users/list";
	}
	//検索
	@GetMapping("/search")
	public String search(@RequestParam(defaultValue = "") String keyword,Model model) {
		model.addAttribute("userList",userService.search(keyword));
		model.addAttribute("keyword",keyword);
		return "users/list";
	}
	
}
