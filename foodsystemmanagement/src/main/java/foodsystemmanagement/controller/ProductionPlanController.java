package foodsystemmanagement.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import foodsystemmanagement.entity.ProductionPlan;
import foodsystemmanagement.service.OrderService;
import foodsystemmanagement.service.ProductService;
import foodsystemmanagement.service.ProductionPlanService;
import foodsystemmanagement.service.UserService;

@Controller
public class ProductionPlanController {
	private final ProductionPlanService productionPlanService;
	private final OrderService orderService;
	private final ProductService productService;
	private final UserService userService;
	
	public ProductionPlanController(
	        ProductionPlanService productionPlanService,
	        OrderService orderService,
	        ProductService productService,
	        UserService userService) {

	    this.productionPlanService = productionPlanService;
	    this.orderService = orderService;
	    this.productService = productService;
	    this.userService = userService;
	}

	//製造計画一覧
	@GetMapping("/productionPlans/list")
		public String list(Model model) {
		List<ProductionPlan>productionPlanList = productionPlanService.findAll();
		model.addAttribute("productionPlanList",productionPlanList);
		return "productionPlans/list";
	}
	
	//登録
	@GetMapping("/productionPlans/register")
		public String newProductionPlan(Model model) {
		model.addAttribute("productionPlan",new ProductionPlan());
		return "productionPlans/register";
	}
	@PostMapping("/productionPlans")
	public String create(
	        @Valid @ModelAttribute("productionPlan") ProductionPlan productionPlan,
	        BindingResult result,
	        @RequestParam Long orderId,
	        @RequestParam Long productId,
	        @RequestParam Long assignedUserId,
	        RedirectAttributes redirectAttributes) {
		/*
		if(productionPlanService.existsOrder(productionPlan.getOrder())) {
			result.rejectValue("order","duplicate","この注文は既に製造計画に登録されています。");
		}
		*/
		if(result.hasErrors()) {
			return "productionPlans/register";
		}
		productionPlan.setOrder(orderService.findById(orderId));
		productionPlan.setProduct(productService.findById(productId));
		productionPlan.setAssignedUser(userService.findById(assignedUserId));
		
		productionPlanService.save(productionPlan);
		redirectAttributes.addFlashAttribute("message", "製造計画を登録しました。");
			return"redirect:/productionPlans/list";
		}
	//編集
	@GetMapping("/productionPlans/edit/{id}")
		public String edit(@PathVariable Long id,Model model){
			ProductionPlan productionPlan=productionPlanService.findById(id);
			model.addAttribute("productionPlan",productionPlan);
			return "productionPlans/edit";
		}
	@PostMapping("/productionPlans/update")
	public String update(
	        ProductionPlan productionPlan,
	        @RequestParam Long orderId,
	        @RequestParam Long productId,
	        @RequestParam Long assignedUserId,
	        RedirectAttributes redirectAttributes){
			productionPlan.setOrder(orderService.findById(orderId));
			productionPlan.setProduct(productService.findById(productId));
			productionPlan.setAssignedUser(userService.findById(assignedUserId));
			productionPlanService.update(productionPlan);
			redirectAttributes.addFlashAttribute("message", "製造計画を修正しました。");
			return "redirect:/productionPlans/list";
		}
	
	//削除
	@PostMapping("/productionPlans/delete/{id}")
		public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes) {
		productionPlanService.delete(id);
		redirectAttributes.addFlashAttribute("message", "製造計画を削除しました。");
		return "redirect:/productionPlans/list";
	}
	
	/*
	//検索
	@GetMapping("/productionPlans/search")
		public String search(@RequestParam String keyword,Model model) {
		model.addAttribute("productionPlanList",productionPlanService.search(keyword));
		model.addAttribute("keyword",
				keyword);
		return "productionPlans/list";
	}
	*/
}
