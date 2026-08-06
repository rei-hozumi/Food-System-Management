package foodsystemmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import foodsystemmanagement.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order>findByOrderNumberContaining(String keyword);
	
	//ダッシュボード用(未完了受注、売上)
	List<Order>findByStatusNot(String status);
    // 指定日の受注
    List<Order> findByOrderDate(LocalDate orderDate);
    // 期間内の受注
    List<Order> findByOrderDateBetween(LocalDate start,LocalDate end);
    //最新の注文番号の取得
	Order findTopByOrderByIdDesc();

	/*	@Query("""
				SELECT COALESCE(SUM(o.totalPrice),0)
				FROM Order o
				WHERE o.orderDate = :date
				""")
				int sumSalesByDate(
				        @Param("date") LocalDate date
				);
		@Query("""
				SELECT COALESCE(SUM(o.totalPrice),0)
				FROM Order o
				WHERE o.orderDate BETWEEN :start AND :end
				""")
				int sumSalesBetween(
				        @Param("start") LocalDate start,
				        @Param("end") LocalDate end
				);
		*/
}