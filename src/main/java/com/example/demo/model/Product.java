package com.example.demo.model;

public class Product {
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Integer productAmount;
	private String productDetail;
	
	public Product() {
		super();
	}

	public Product(Integer productId, String productName, Integer productPrice, Integer productAmount, String productDetail) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productAmount = productAmount;
		this.productDetail = productDetail;
	}

	public Integer getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}
	
	public Integer getProductPrice() {
		return productPrice;
	}
	
	public Integer getProductAmount() {
		return productAmount;
	}
	
	public String getProductDetail() {
		return productDetail;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	
	
}
