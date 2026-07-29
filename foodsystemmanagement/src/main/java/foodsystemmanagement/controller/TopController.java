package foodsystemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import foodsystemmanagement.dto.TopDashboardDto;
import foodsystemmanagement.service.TopService;

@Controller
public class TopController {
	private final TopService topService;
	public TopController(TopService topService) {
		this.topService = topService;
	}

    @GetMapping("/top")
    public String top(Model model){
    	TopDashboardDto dashboard = topService.getDashboardData();
    	model.addAttribute("dashboard",dashboard);
        return "top";
    }
}