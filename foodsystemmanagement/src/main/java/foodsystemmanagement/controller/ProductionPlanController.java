package foodsystemmanagement.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import foodsystemmanagement.entity.Order;
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
	@PreAuthorize("hasAnyRole('ADMIN','PLANNER','WORKER')")
	public String list(Model model) {
		List<ProductionPlan> productionPlanList = productionPlanService.findAll();
		model.addAttribute("productionPlanList", productionPlanList);
		return "productionPlans/list";
	}

	//登録
	@GetMapping("/productionPlans/register")
	@PreAuthorize("hasAnyRole('ADMIN','PLANNER')")
	public String newProductionPlan(Model model) {
		model.addAttribute("productionPlan", new ProductionPlan());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("orders", orderService.findAll());
		return "productionPlans/register";
	}

	@PostMapping("/productionPlans/register")
	@PreAuthorize("hasAnyRole('ADMIN','PLANNER')")
	public String create(
			@Valid @ModelAttribute("productionPlan") ProductionPlan productionPlan,
			BindingResult result,
			@RequestParam(required = false) Long orderId,
			 Model model,
			RedirectAttributes redirectAttributes) {

	    if (orderId == null) {
	        result.rejectValue("order", "required", "注文番号を選択してください");
	    } else {
	        Order order = orderService.findById(orderId);
	        productionPlan.setOrder(order);
	        productionPlan.setProduct(order.getProduct());
	    }
		if (result.hasErrors()) {
			model.addAttribute("productionPlan", productionPlan);
			model.addAttribute("users", userService.findAll());
			model.addAttribute("products", productService.findAll());
			model.addAttribute("orders", orderService.findAll());
			return "productionPlans/register";
		}
		productionPlanService.save(productionPlan);
		redirectAttributes.addFlashAttribute("message", "製造計画を登録しました。");
		return "redirect:/productionPlans/list";
	}

	//編集
	@GetMapping("/productionPlans/edit/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PLANNER','WORKER')")
	public String edit(@PathVariable Long id, Model model) {
		ProductionPlan productionPlan = productionPlanService.findById(id);

		System.out.println(productionPlan.getProductionDate());
		System.out.println(productionPlan.getProductionDate().getClass());

		model.addAttribute("productionPlan", productionPlan);
		model.addAttribute("users", userService.findAll());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("orders", orderService.findAll());
		return "productionPlans/edit";
	}

	@PostMapping("/productionPlans/update")
	@PreAuthorize("hasAnyRole('ADMIN','PLANNER','WORKER')")
	public String update(@Valid @ModelAttribute("productionPlan") ProductionPlan productionPlan,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("productionPlan", productionPlan);
			model.addAttribute("users", userService.findAll());
			model.addAttribute("products", productService.findAll());
			model.addAttribute("orders", orderService.findAll());
			return "productionPlans/edit";
		}
		Long orderId = productionPlan.getOrder().getId();
		Long assignedUserId = productionPlan.getAssignedUser().getId();
		
		Order order = orderService.findById(orderId);
		LocalDate productionDate = productionPlan.getProductionDate();
		
		productionPlan.setAssignedUser(userService.findById(assignedUserId));
		productionPlan.setOrder(order);
		productionPlan.setProduct(order.getProduct());
		productionPlan.setProductionDate(productionDate);

		productionPlanService.update(productionPlan);
		redirectAttributes.addFlashAttribute("message", "製造計画を修正しました。");
		return "redirect:/productionPlans/list";
	}

	//削除
	@PostMapping("/productionPlans/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PLANNER')")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		productionPlanService.delete(id);
		redirectAttributes.addFlashAttribute("message", "製造計画を削除しました。");
		return "redirect:/productionPlans/list";
	}

	//検索
	@GetMapping("/productionPlans/search")
	@PreAuthorize("hasAnyRole('ADMIN','PLANNER''WORKER')")
	public String search(@RequestParam(defaultValue = "") String keyword, Model model) {
		model.addAttribute("productionPlanList", productionPlanService.search(keyword));
		model.addAttribute("keyword", keyword);
		return "productionPlans/list";
	}

}
