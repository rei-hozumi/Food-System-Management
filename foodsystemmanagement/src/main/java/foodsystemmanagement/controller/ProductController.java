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

import foodsystemmanagement.entity.Product;
import foodsystemmanagement.service.ProductService;

@Controller
public class ProductController {
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	//商品一覧
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
		public String create(@Valid @ModelAttribute("product") Product product,BindingResult result,RedirectAttributes redirectAttributes) {
		
		if(productService.existsProductCode(product.getProductCode())) {
			result.rejectValue("productCode","duplicate","この商品コードは既に登録されています。");
		}
		
		if(result.hasErrors()) {
			return "products/register";
		}
		
		productService.save(product);
		redirectAttributes.addFlashAttribute("message", "商品を登録しました。");
			return"redirect:/products/list";
		}
	//商品編集
	@GetMapping("/products/edit/{id}")
		public String edit(@PathVariable Long id,Model model){
			Product product=productService.findById(id);
			model.addAttribute("product",product);
			return "products/edit";
		}
	@PostMapping("/products/update")
		public String update(Product product,RedirectAttributes redirectAttributes) {
			productService.update(product);
			redirectAttributes.addFlashAttribute("message", "商品を修正しました。");
			return "redirect:/products/list";
		}
	
	//商品削除
	@PostMapping("/products/delete/{id}")
		public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes) {
		productService.delete(id);
		redirectAttributes.addFlashAttribute("message", "商品を削除しました。");
		return "redirect:/products/list";
	}
	
	//商品検索
	@GetMapping("/products/search")
		public String search(@RequestParam String keyword,Model model) {
		model.addAttribute("productList",productService.search(keyword));
		model.addAttribute("keyword",
				keyword);
		return "products/list";
	}
	

}

