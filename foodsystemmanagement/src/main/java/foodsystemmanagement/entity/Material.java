package foodsystemmanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name="materials")
public class Material {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "商品コードを入力してください")
	@Column(name="material_code",nullable=false,length=30)
	private String materialCode;
	
	@NotBlank(message = "商品名を入力してください")
	@Column(name="material_name",nullable=false,length=50)
	private String materialName;
	
	@NotNull(message = "価格を入力してください")
	@Positive(message = "０より大きい値を入力してください")
	@Column(nullable=false,precision=10,scale=2)
	private BigDecimal price;
	
	@NotNull(message = "賞味期限を入力してください")
	@Column(name="expiration_date")
	private LocalDate expirationDate;
	
	@NotNull(message = "単位を入力してください")
	@Column(nullable=false)
	private String unit;
	
	@Column(nullable=false)
	@Positive(message = "0より大きい数字を入力してください")
	private int stockQuantity;
	
	@NotNull(message = "規格を入力してください")
	@Column(nullable=false)
	private String standard;
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id=id;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode=materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName=materialName;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit=unit;	
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}
	
	public void setStockQuantity(int stockQuantity){
		this.stockQuantity = stockQuantity;
	}
	
	public String getStandard() {
		return standard;
	}
	
	public void setStandard(String standard){
		this.standard = standard;
	}
}

