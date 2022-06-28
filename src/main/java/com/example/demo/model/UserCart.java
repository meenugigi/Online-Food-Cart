package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class UserCart {

	@Id
	@GeneratedValue
	private Long cartID;
	private String userName;
//	private Long itemID;
//	private String itemName;
//	private float itemPrice;
	private int itemQuantity;
//	private String itemImage;
	private float totalAmount;

	
	@ManyToOne
	private ItemInfo items;
	
	
//	@OneToOne(mappedBy = "cart", targetEntity = Orders.class)
//	private Orders orders;
//	
	
	
	public Long getCartID() {
		return cartID;
	}
	public void setCartID(Long cartID) {
		this.cartID = cartID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

//	
//	public Long getItemID() {
//		return itemID;
//	}
//	public void setItemID(Long itemID) {
//		this.itemID = itemID;
//	}
//	public String getItemName() {
//		return itemName;
//	}
//	public void setItemName(String itemName) {
//		this.itemName = itemName;
//	}
//
//	
//	
//	public String getItemImage() {
//		return itemImage;
//	}
//	public void setItemImage(String itemImage) {
//		this.itemImage = itemImage;
//	}
//	public float getItemPrice() {
//		return itemPrice;
//	}
//	public void setItemPrice(float itemPrice) {
//		this.itemPrice = itemPrice;
//	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	
	
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	
	public ItemInfo getItems() {
		return items;
	}
	public void setItems(ItemInfo items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "UserCart [cartID=" + cartID + ", userName=" + userName + ", itemQuantity=" + itemQuantity
				+ ", totalAmount=" + totalAmount + ", items=" + items + "]";
	}



	

	

	
	
	
}
