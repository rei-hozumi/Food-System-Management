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
@Table(name="materials")
public class Material {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="material_code",nullable=false,length=30)
	private String materialCode;
	
	@Column(name="material_name",nullable=false,length=50)
	private String materialName;
	
	@Column(nullable=false,precision=5,scale=2)
	private BigDecimal price;
	
	@Column(name="expiration_date")
	private LocalDate expirationDate;
	
	@Column(nullable=false)
	private String unit;
	
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
	}
