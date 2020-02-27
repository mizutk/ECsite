package com.internousdev.ecsite.dto;

public class MyPageDTO {
	private String itemName;
	private String totalPrice;
	private String totalCount;
	private String payment;
	private String userName;
	private String id;
	private String insert_date;

	/* ItemName */
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/* TotalPrice */
	public String getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	/* TotalCount */
	public String getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	/* Payment */
	public String getPayment() {
		return this.payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	/* UserName */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/* Id */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/* Insert_date */
	public String getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}
}