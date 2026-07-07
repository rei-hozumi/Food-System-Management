package foodsystemmanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="product_code",nullable=false,length=30)
	private String productCode;
	
	@Column(name="product_name",nullable=false,length=50)
	private String productName;
	
	@Column(nullable=false,precision=5,scale=2)
	private BigDecimal price;
	
	@Column(name="expiration_date")
	private LocalDate expirationDate;
	
	public Product() {}
	
	public Product(Long id,String productCode,String productName,BigDecimal price,LocalDate expirationDate) {
		this.id=id;
		this.productCode=productCode;
		this.productName=productName;
		this.price=price;
		this.expirationDate=expirationDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id=id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode=productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName=productName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price=price;
	}	
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate=expirationDate;	
	}

}
