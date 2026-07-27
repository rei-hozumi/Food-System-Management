package foodsystemmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import foodsystemmanagement.entity.Material;
import foodsystemmanagement.repository.MaterialRepository;

@Service
public class MaterialService {
	private final MaterialRepository materialRepository;
	
	public MaterialService(MaterialRepository materialRepository) {
		this.materialRepository=materialRepository;
	}
	
	//原材料一覧取得
	public List<Material>findAll(){
		return materialRepository.findAll();
	}
	//登録
	public void save(Material material) {
		materialRepository.save(material);
	}
	//編集
	public Material findById(long id) {
		return materialRepository.findById(id).orElseThrow();
	}
	public void update(Material material) {
		materialRepository.save(material);
	}
	//削除
	public void delete(Long id) {
		materialRepository.deleteById(id);
	}
	//検索
	public List<Material> search(String keyword){
		System.out.println("検索文字："+keyword);
		
		List<Material>list = materialRepository.findByMaterialNameContaining(keyword);
		System.out.println("件数："+list.size());
		return list;
	}
	//重複チェック
	public boolean existsMaterialCode(String materialCode) {
		return materialRepository.findByMaterialCode(materialCode) != null;
	}
	
}
