package shopping.domain;

import java.math.BigDecimal;

/**
 * Product Entity
 * */
public class Product {
	private Long id;
	private String productName;
	private String brand;
	private String supplier;
	private	BigDecimal salePrice;
	private	BigDecimal costPrice;
	private Double cutoff;
	private Long dirId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public Double getCutoff() {
		return cutoff;
	}
	public void setCutoff(Double cutoff) {
		this.cutoff = cutoff;
	}
	public Long getDirId() {
		return dirId;
	}
	public void setDirId(Long dirId) {
		this.dirId = dirId;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName
				+ ", brand=" + brand + ", supplier=" + supplier
				+ ", salePrice=" + salePrice + ", costPrice=" + costPrice
				+ ", cutoff=" + cutoff + ", dirId=" + dirId + "]";
	}
}
