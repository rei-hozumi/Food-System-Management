package foodsystemmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
