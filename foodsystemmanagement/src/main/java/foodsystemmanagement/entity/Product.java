package foodsystemmanagement.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "商品コードを入力してください")
	@Column(name="product_number",nullable=false,length=30)
	private String productNumber;
	
	@NotBlank(message = "商品名を入力してください")
	@Column(name="product_name",nullable=false,length=50)
	private String productName;
	
	@NotNull(message = "価格を入力してください")
	@Positive(message = "０より大きい値を入力してください")
	@Column(nullable=false,precision=5,scale=2)
	private BigDecimal price;
	
	/*	@NotNull(message = "賞味期限を入力してください")
		@Column(name="expiration_date")
		private LocalDate expirationDate;*/
	
	public Product() {}
	
	public Product(Long id,String productNumber,String productName,BigDecimal price/*,LocalDate expirationDate*/) {
		this.id=id;
		this.productNumber=productNumber;
		this.productName=productName;
		this.price=price;
//		this.expirationDate=expirationDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id=id;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber=productNumber;
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
	/*	public LocalDate getExpirationDate() {
			return expirationDate;
		}
		public void setExpirationDate(LocalDate expirationDate) {
			this.expirationDate=expirationDate;	
		}
	*/
}
