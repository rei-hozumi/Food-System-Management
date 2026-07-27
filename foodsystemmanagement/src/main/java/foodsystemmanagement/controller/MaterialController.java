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

import foodsystemmanagement.entity.Material;
import foodsystemmanagement.service.MaterialService;

@Controller
public class MaterialController {
	private final MaterialService materialService;
	
	public MaterialController(MaterialService materialService) {
		this.materialService=materialService;
		
	}
	
	//一覧
	@GetMapping("/materials/list")
	public String list(Model model) {
		List<Material>materialList = materialService.findAll();
		model.addAttribute("materialList",materialList);
		return "materials/list";
	}
	
	//登録
	@GetMapping("/materials/register")
		public String newMaterial(Model model) {
		model.addAttribute("material",new Material());
		return "materials/register";
	}
	@PostMapping("/materials")
		public String create(@Valid @ModelAttribute("material") Material material,BindingResult result,RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()) {
			return "material/register";
		}
		materialService.save(material);
		redirectAttributes.addFlashAttribute("message", "原材料を登録しました。");
			return "redirect:/materials/list";
		}
	@GetMapping("/materials/registrationcomplete")
		public String registrationComplete() {
		return "materials/registrationcomplete";
	}
	
	//商品編集
	@GetMapping("/materials/edit/{id}")
		public String edit(@PathVariable Long id,Model model) {
		Material material = materialService.findById(id);
		model.addAttribute("material",material);
		return "materials/edit";
	}
	@PostMapping("/materials/update")
		public String update(Material material,RedirectAttributes redirectAttributes) {
		materialService.update(material);
		redirectAttributes.addFlashAttribute("message", "原材料を修正しました。");
		return "redirect:/materials/list";
	}
	
	//商品削除
	@PostMapping("/materials/delete/{id}")
	public String delete(@PathVariable Long id,
	                     RedirectAttributes redirectAttributes) {
	    materialService.delete(id);
	    redirectAttributes.addFlashAttribute("message", "原材料を削除しました。");
	    return "redirect:/materials/list";
	}
	
	//商品検索
	@GetMapping("/materials/search")
		public String search(@RequestParam String  keyword,Model model) {
		model.addAttribute("materialList",materialService.search(keyword));
		model.addAttribute("keyword",keyword);
		return "materials/list";
	}
}
