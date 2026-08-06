package foodsystemmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.Order;
import foodsystemmanagement.entity.ProductionPlan;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan,Long>{
	List <ProductionPlan>findByProduct_ProductNameContaining(String keyword);
	ProductionPlan findByOrder(Order order);
	List<ProductionPlan>findByProductionDate(LocalDate productionDate);
    
	//受注削除時に先に製造計画を削除
    @Transactional
	void deleteByOrderId(Long orderId);
    
    List<ProductionPlan> findByAssignedUserId(Long userId);
}
