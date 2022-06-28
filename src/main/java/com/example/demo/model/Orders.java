package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Orders {
	
	@Id
	private String orderID;
	private String userEmail;
	private int quantity;
	private String contact;
//	private String itemOrdered;
	private Long itemID;
//	private float itemAmount;
	private String orderDate;
	
//	@OneToOne
//	private UserCart cart;

//	@ManyToOne
//	private ItemInfo itemsOrdered;
	
	
	public String getOrderID() {
		return orderID;
	}


	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}

//
//	public String getItemOrdered() {
//		return itemOrdered;
//	}
//
//
//	public void setItemOrdered(String itemOrdered) {
//		this.itemOrdered = itemOrdered;
//	}
//
//
//
//
//
//	public float getItemAmount() {
//		return itemAmount;
//	}
//
//
//	public void setItemAmount(float itemAmount) {
//		this.itemAmount = itemAmount;
//	}
//
//
	public Long getItemID() {
		return itemID;
	}


	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}





	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


//	public ItemInfo getItemsOrdered() {
//		return itemsOrdered;
//	}
//
//
//	public void setItemsOrdered(ItemInfo itemsOrdered) {
//		this.itemsOrdered = itemsOrdered;
//	}


	
	
	
//	public UserCart getCart() {
//		return cart;
//	}
//
//
//	public void setCart(UserCart cart) {
//		this.cart = cart;
//	}


//	@Override
//	public String toString() {
//		return "Orders [orderID=" + orderID + ", userEmail=" + userEmail + ", quantity=" + quantity + ", contact="
//				+ contact + ", orderDate=" + orderDate + ", cart=" + cart + "]";
//	}

//
////	@Override
////	public String toString() {
////		return "Orders [orderID=" + orderID + ", userEmail=" + userEmail + ", quantity=" + quantity + ", contact="
////				+ contact + ", itemOrdered=" + itemOrdered + ", itemID=" + itemID + ", itemAmount=" + itemAmount
////				+ ", orderDate=" + orderDate + "]";
////	}
	
	

}
