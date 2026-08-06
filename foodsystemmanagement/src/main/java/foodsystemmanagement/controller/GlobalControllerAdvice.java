package foodsystemmanagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import foodsystemmanagement.entity.LoginUser;

@ControllerAdvice
public class GlobalControllerAdvice {
	 @ModelAttribute
	 public void addLoginUser(Model model) {

	        Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null
	                && authentication.getPrincipal() instanceof LoginUser loginUser) {
	            model.addAttribute("loginUserName",
	                    loginUser.getUser().getUserName());
	        }
	    }
	}