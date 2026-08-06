package foodsystemmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

	List<Product> findByProductNameContaining(String keyword);
	
	Product findByProductNumber(String productNumber);
	
    boolean existsByProductNumber(String productNumber);
}
