package foodsystemmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.Order;
import foodsystemmanagement.entity.ProductionPlan;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan,Long>{
	/*List <ProductionPlan>findByProductionPlanNameContaining(String keyword);*/
	ProductionPlan findByOrder(Order order);
}
