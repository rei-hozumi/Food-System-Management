package foodsystemmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.Material;


public interface MaterialRepository extends JpaRepository<Material,Long> {

	List<Material> findByMaterialNameContaining(String keyword);
	
	Material findByMaterialCode(String MaterialCode);
}
