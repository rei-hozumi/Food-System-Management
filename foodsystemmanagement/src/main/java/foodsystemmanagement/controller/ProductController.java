package foodsystemmanagement.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import foodsystemmanagement.entity.Product;
import foodsystemmanagement.service.ProductService;

@Controller
public class ProductController {
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	//商品一覧画面
	@GetMapping("/products/list")
		public String list(Model model) {
			List<Product>productList = productService.findAll();
			model.addAttribute("productList",productList);
			return"products/list";
		}
	
	//商品登録
	@GetMapping("/products/register")
		public String newProduct(Model model) {
		model.addAttribute("product",new Product());
		return "products/register";
	}
	@PostMapping("/products")
		public String create(Product product) {
			productService.save(product);
			return"redirect:/products";
		}
		
	//商品編集
	@GetMapping("/products/edit/{id}")
		public String edit(@PathVariable Long id,Model model){
			Product product=productService.findById(id);
			model.addAttribute("products",product);
			return "products/edit";
		}
	
	@PostMapping("/products/update")
		public String update(Product product) {
			productService.update(product);
			return "redirect:/products/list";
		}
	
}
