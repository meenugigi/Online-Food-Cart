package com.example.demo.service;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.remoting.soap.SoapFaultException;
import org.springframework.stereotype.Service;

import com.example.demo.model.ItemInfo;
import com.example.demo.model.Orders;
import com.example.demo.model.Restaurants;
import com.example.demo.model.UserCart;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCartServiceImpl implements FoodCartService{


	
	private ItemRepository itemRepo;
	private CartRepository cartRepo;
	private OrderRepository orderRepo;
	private RestaurantRepository restRepo;

	



	public FoodCartServiceImpl(ItemRepository itemRepo, CartRepository cartRepo, OrderRepository orderRepo,
			RestaurantRepository restRepo) {
		super();
		this.itemRepo = itemRepo;
		this.cartRepo = cartRepo;
		this.orderRepo = orderRepo;
		this.restRepo = restRepo;
	}

	@Override
	public void saveItem(ItemInfo item) {
		itemRepo.save(item);		
	}

	
	@Override
	public ItemInfo getItemById(long id) {
		Optional<ItemInfo> optional = itemRepo.findById(id);
		ItemInfo product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException(" Item not found for id :: " + id);
		}
		return product;
	}

	
	@Override
	public void saveItemToCart(UserCart item) {
		// TODO Auto-generated method stub
		cartRepo.save(item);
		
	}

	@Override
	public void deleteItem(long id) {
		// TODO Auto-generated method stub
		itemRepo.deleteById(id);
	}

	@Override
	public List<UserCart> showMyCart(String userName) {
		// TODO Auto-generated method stub
		return cartRepo.showCartItems(userName);
	}

	@Override
	public void removeFromCart(long cartID) {
		// TODO Auto-generated method stub
		cartRepo.deleteById(cartID);
	}

	@Override
	public int getItemCountInCart_add(String userName, long itemID) {
		// TODO Auto-generated method stub
		return cartRepo.getItemCount_Cart_AddItems(userName, itemID);
	}

	@Override
	public void AddItemQuantityInCart(String userName, long itemID, int quantity) {
		// TODO Auto-generated method stub
		cartRepo.AddItemQuantityInCart(userName, itemID, quantity);
	}

	@Override
	public void ReduceItemQuantityInCart(String userName, long cartID, int quantity) {
		// TODO Auto-generated method stub
		cartRepo.ReduceItemQuantityInCart(userName, cartID, quantity);
	}


	@Override
	public int getItemCountInCart_remove(String userName, Long cartID) {
		// TODO Auto-generated method stub
		return cartRepo.getItemCount_Cart_RemoveItems(userName, cartID);
	}

	@Override
	public float getCartTotal(String userName) {
		// TODO Auto-generated method stub
		return cartRepo.getCartTotalAmount(userName);
	}

	@Override
	public List<UserCart> getAllCartItems(String userName) {
		// TODO Auto-generated method stub
		return cartRepo.getAllItemsInCart(userName);
	}

	@Override
	public void saveOrder(Orders order) {
		// TODO Auto-generated method stub
		orderRepo.save(order);
	}

	@Override
	public void deleteOrderedItems(Long cartID) {
		// TODO Auto-generated method stub
		cartRepo.deleteOrderedItemsFromCart(cartID);
	}

	@Override
	public void saveRestaurantToDB(Restaurants rest) {
		// TODO Auto-generated method stub
		restRepo.save(rest);
	}

	@Override
	public List<Restaurants> findAllRestaurants() {
		// TODO Auto-generated method stub
		return restRepo.findAll();
	}

	@Override
	public List<ItemInfo> findRestaurantMenu(Long restaurantID) {
		// TODO Auto-generated method stub
		return itemRepo.findRestaurantsMenu(restaurantID);
	}

	@Override
	public void deleteRestaurant(long restaurantID) {
		// TODO Auto-generated method stub
		restRepo.deleteById(restaurantID);
	}

//	@Override
//	public void deleteMenuFromRestaurant(Long restaurantID) {
//		// TODO Auto-generated method stub
//		itemRepo.deleteRestaurantMenu(restaurantID);
//	}

	@Override
	public Restaurants getRestaurantById(Long restaurantID) {
		// TODO Auto-generated method stub
		Optional<Restaurants> optional = restRepo.findById(restaurantID);
		Restaurants restaurants = null;
		if (optional.isPresent()) {
			restaurants = optional.get();
		} else {
			throw new RuntimeException(" Restaurant not found for id :: " + restaurantID);
		}
		return restaurants;
	}

	@Override
	public List<String> getRestaurantNames() {
		// TODO Auto-generated method stub
		return restRepo.getAllRestaurantNames();
	}
//
//	@Override
//	public Long getRestaurantID(String restaurantName) {
//		// TODO Auto-generated method stub
//		return restRepo.getRestaurantID(restaurantName);
//	}
//
//	@Override
//	public void updateRestaurantNameInItemInfo(Long id, String restaurantName) {
//		// TODO Auto-generated method stub
//		itemRepo.updateRestaurantName(id, restaurantName);
//	}
//
//	@Override
//	public String getRestaurantName(Long restaurantID) {
//		// TODO Auto-generated method stub
//		return restRepo.getRestaurantName(restaurantID);
//	}

	@Override
	public Restaurants getRestaurantByName(String restaurantName) {
		// TODO Auto-generated method stub
		return restRepo.getRestaurantByName(restaurantName);
	}

	







	

}
