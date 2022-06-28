package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Restaurants implements Serializable{

	@Id
	@GeneratedValue
	private Long restaurantID;
	private String restaurantName;
	private String restCoverImage;
	
	        
	@OneToMany(mappedBy = "restaurants", fetch = FetchType.LAZY, targetEntity = ItemInfo.class, 
			cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemInfo> menu;
	
	
	
	public Long getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(Long restaurantID) {
		this.restaurantID = restaurantID;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	
	
	
	public String getRestCoverImage() {
		return restCoverImage;
	}
	public void setRestCoverImage(String restCoverImage) {
		this.restCoverImage = restCoverImage;
	}
	@Override
	public String toString() {
		return "Restaurants [restaurantID=" + restaurantID + ", restaurantName=" + restaurantName + ", restCoverImage="
				+ restCoverImage + "]";
	}
	
	
	
}
