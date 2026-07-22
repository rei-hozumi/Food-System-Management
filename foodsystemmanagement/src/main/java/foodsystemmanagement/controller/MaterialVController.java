package foodsystemmanagement.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import foodsystemmanagement.entity.Material;
import foodsystemmanagement.service.MaterialService;

@Controller
public class MaterialVController {
	private final MaterialService materialService;
	
	public MaterialController(MaterialService materialService) {
		this.materialService=materialService;
	}
	
	//一覧
	@GetMapping("/materials/list")
	public String list(Model model) {
		List<Material>materialList = materialService.findAll();
		return "materials/list";
	}
	
	//登録
	@GetMapping("/materials/register")
		public String newMaterial(Model model) {
		model.addAttribute("material",new Material());
		return "materials/register";
	}
	@PostMapping("/materials")
		public String create(@Valid @ModelAttribute("material") Material material,BindingResult result){
		
		if(result.hasErrors()) {
			return "material\register";
		}
		materialService.save(material);
			return "redirect:/materials/registrationcomplete";
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
		public String update(Material material) {
		materialService.update(material);
		return "redirect:/materials/list";
	}
	
	//商品削除
	@PostMapping("/materials/delete/{id}")
		public String delete(@PathVariable Long id) {
		materialService.delete(id);
		return "redirect:/material/list";
	}
	
	//商品検索
	@GetMapping("/materials/search")
		public String search(@RequestParam String  keyword,Model model) {
		model.addAttribute("materialList",materialService.search(keyword));
		model.addAttribute("keyword",keyword);
		return "materials/list";
	}
}
