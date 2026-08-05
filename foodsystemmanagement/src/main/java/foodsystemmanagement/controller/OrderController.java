package foodsystemmanagement.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import foodsystemmanagement.entity.Order;
import foodsystemmanagement.repository.OrderRepository;
import foodsystemmanagement.repository.ProductRepository;
import foodsystemmanagement.service.OrderService;

@Controller
@RequestMapping("/orders")
@PreAuthorize("hasAnyRole('ADMIN','PLANNER')")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    
    public OrderController(OrderService orderService,OrderRepository orderRepository,ProductRepository productRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    
    // 受注一覧
    @GetMapping("/list")
    public String list(Model model) {
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orderList", orderList);
        return "orders/list";
    }
    // 登録画面
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("order", new Order());
        
        //product一覧取得
        model.addAttribute("products",productRepository.findAll());
        
        return "orders/register";
    }
    // 登録
    @PostMapping("/orders/register")
    public String create(
            @Valid @ModelAttribute("order") Order order,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "orders/register";
        }
        orderService.save(order);
        redirectAttributes.addFlashAttribute(
                "message", "注文を登録しました。");
        return "redirect:/orders/list";
    }
    // 編集画面
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        //product一覧取得
        model.addAttribute("products",productRepository.findAll());
        return "orders/edit";
    }
    //注文情報を自動取得するAPI
    @GetMapping("/api/{id}")
    @ResponseBody
    public Order getOrder(@PathVariable Long id) {
    	return orderService.findById(id);
    }
    // 更新
    @PostMapping("/update")
    public String update(
            @ModelAttribute("order") Order order,
            RedirectAttributes redirectAttributes) {
        orderService.update(order);
        redirectAttributes.addFlashAttribute(
                "message", "注文を更新しました。");
        return "redirect:/orders/list";
    }

    // 削除
    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        orderService.delete(id);
        redirectAttributes.addFlashAttribute(
                "message", "注文を削除しました。");
        return "redirect:/orders/list";
    }
    //検索
    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String keyword,Model model) {
    	model.addAttribute("orderList",orderService.search(keyword));
    	model.addAttribute("keyword",keyword);
    	return "orders/list";
    }
}