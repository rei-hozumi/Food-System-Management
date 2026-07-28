package foodsystemmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Order findTopByOrderByIdDesc();
}