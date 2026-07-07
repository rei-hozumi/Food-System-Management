package foodsystemmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="production_plans")
public class ProductionPlan {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name="oeder_id")
private Order order;

@ManyToOne
@JoinColumn(name="product_id")
private Product product;

@Column(name="planned_quantity")
private Integer plannedQuantity;

@ManyToOne
@JoinColumn(name="assigned_user_id")
private User assignedUser;

@Column(length = 10)
private String status;

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


}
