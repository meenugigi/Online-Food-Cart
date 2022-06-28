package com.example.demo.service;

import com.example.demo.model.ItemInfo;
import com.example.demo.model.Orders;
import com.example.demo.model.Restaurants;
import com.example.demo.model.UserCart;

import java.util.List;
import java.util.Optional;

public interface FoodCartService {

	void saveItem(ItemInfo item);

	ItemInfo getItemById(long id);

	void saveItemToCart(UserCart item);

	void deleteItem(long id);

	List<UserCart> showMyCart(String userName);

	void removeFromCart(long cartID);

	int getItemCountInCart_add(String userName, long itemID);

	void AddItemQuantityInCart(String userName, long itemID, int quantity);

	void ReduceItemQuantityInCart(String userName, long cartID, int quantity);

	int getItemCountInCart_remove(String userName, Long cartID);

	float getCartTotal(String userName);

	List<UserCart> getAllCartItems(String userName);

	void saveOrder(Orders order);
//
	void deleteOrderedItems(Long cardID);

	void saveRestaurantToDB(Restaurants rest);

	List<Restaurants> findAllRestaurants();

	List<ItemInfo> findRestaurantMenu(Long restaurantID);

	void deleteRestaurant(long restaurantID);

//	void deleteMenuFromRestaurant(Long restaurantID);

	Restaurants getRestaurantById(Long restaurantID);

	List<String> getRestaurantNames();

//	Long getRestaurantID(String restaurantName);

	

//	void updateRestaurantNameInItemInfo(Long id, String restaurantName);

//	String getRestaurantName(Long restaurantID);

	Restaurants getRestaurantByName(String restaurantName);

	
	

	
	
	
}
