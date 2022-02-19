package com.example.Ecommerce;

import org.springframework.stereotype.Component;

@Component
public class Linepaydetail{
	private String oneTimeKey;
	private int amount;
	private String orderId;
	private String currency;
	private String productName;

	public String getOneTimeKey(){
		return oneTimeKey;
	}

	public int getAmount(){
		return amount;
	}

	public String getOrderId(){
		return orderId;
	}

	public String getCurrency(){
		return currency;
	}

	public String getProductName(){
		return productName;
	}

	public void setOneTimeKey(String oneTimeKey) {
		this.oneTimeKey = oneTimeKey;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
