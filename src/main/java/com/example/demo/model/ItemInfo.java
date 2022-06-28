package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class ItemInfo {
	
	@Id
	@GeneratedValue
	private Long itemID;
	private String itemName;
	private float itemPrice;
	private String itemImage;
	
	@ManyToOne
	private Restaurants restaurants;
	
	
	@OneToMany(mappedBy = "items", fetch = FetchType.LAZY, targetEntity = UserCart.class, 
			cascade = CascadeType.ALL)
	private List<UserCart> cart;

	
	
	
//	@OneToMany(mappedBy = "itemsOrdered", fetch = FetchType.LAZY, targetEntity = Orders.class)
//	private List<Orders> ordersPlaced;
//	
	
	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public Restaurants getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Restaurants restaurants) {
		this.restaurants = restaurants;
	}

	@Override
	public String toString() {
		return "ItemInfo [itemID=" + itemID + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemImage="
				+ itemImage + ", restaurants=" + restaurants + "]";
	}

	

	
	
	
	

}
