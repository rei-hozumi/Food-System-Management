package foodsystemmanagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name="production_plans")
public class ProductionPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@NotNull(message = "製造数量を入力してください")
	@Positive
	@Column(name="planned_quantity")
	private Integer plannedQuantity;
	
	@NotNull(message = "担当者を選択してください")
	@ManyToOne
	@JoinColumn(name="assigned_user_id")
	private User assignedUser;
	
	@NotBlank(message = "製造ステータスを選択して下さ")
	@Column(length = 10)
	private String status;
	
	@NotNull(message = "製造日を入力してください")
	@Column(nullable = false)
	private LocalDate productionDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Integer getPlannedQuantity() {
		return plannedQuantity;
	}
	
	public void setPlannedQuantity(Integer plannedQuantity) {
		this.plannedQuantity = plannedQuantity;
	}
	
	public User getAssignedUser() {
		return assignedUser;
	}
	
	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDate getProducitonDate() {
		return productionDate;
	}
	
	public void setProductionDate(LocalDate productionDate) {
		this.productionDate = productionDate;
	}

}
