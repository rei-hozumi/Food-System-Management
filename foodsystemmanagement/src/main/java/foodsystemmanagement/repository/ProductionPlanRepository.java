package foodsystemmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.Order;
import foodsystemmanagement.entity.ProductionPlan;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan,Long>{
	List <ProductionPlan>findByProduct_ProductNameContaining(String keyword);
	ProductionPlan findByOrder(Order order);
	List<ProductionPlan>findByProductionDate(LocalDate productionDate);
}
