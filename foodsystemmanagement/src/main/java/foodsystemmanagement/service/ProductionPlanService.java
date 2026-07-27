package foodsystemmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import foodsystemmanagement.entity.ProductionPlan;
import foodsystemmanagement.repository.ProductionPlanRepository;

@Service
public class ProductionPlanService {
	private final ProductionPlanRepository productionPlanRepository;
	
	public ProductionPlanService(ProductionPlanRepository productionPlanRepository) {
		this.productionPlanRepository = productionPlanRepository;
	}
	
	//製造計画一覧取得
	public List<ProductionPlan> findAll(){
	return productionPlanRepository.findAll();
	}
	//登録
	public void save(ProductionPlan productionPlan) {
		productionPlanRepository.save(productionPlan);
	}
	//編集
	public ProductionPlan findById(Long id) {
		return productionPlanRepository.findById(id).orElseThrow();
	}
	public void update(ProductionPlan productionPlan) {
		productionPlanRepository.save(productionPlan);
	}
	//削除
	public void delete(Long id) {
		productionPlanRepository.deleteById(id);
	}
	/*検索
	public List<ProductionPlan>search(String keyword){
		System.out.println("検索文字"+keyword);
		
		List<ProductionPlan>list = productionPlanRepository.findByProductionPlanNameContaining(keyword);
		System.out.println("件数："+list.size());
		return list;
	}
	
	//重複チェック
	public boolean existsOrder(Order order) {
		return productionPlanRepository.findByOrder(Order order) != null;
	}
	*/
}
