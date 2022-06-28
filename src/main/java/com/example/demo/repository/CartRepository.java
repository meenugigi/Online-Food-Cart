package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ItemInfo;
import com.example.demo.model.UserCart;

@Repository
public interface CartRepository extends JpaRepository<UserCart, Long>{


	@Query ("from UserCart where userName = ?1")
	List<UserCart> showCartItems(String userName);

	
	@Query("SELECT COALESCE(SUM(itemQuantity), 0) from UserCart where userName =?1 and items_itemID=?2")
	int getItemCount_Cart_AddItems(String userName, long itemID);
	
	@Transactional
	@Modifying
	@Query("UPDATE UserCart SET itemQuantity=?3 where userName =?1 and items_itemID=?2")
	void AddItemQuantityInCart(String userName, Long itemID, int quantity);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE UserCart SET itemQuantity=?3 where userName =?1 and cartID=?2")
	void ReduceItemQuantityInCart(String userName, Long cartID, int quantity);
		

	
	
	@Query("SELECT COALESCE(SUM(itemQuantity), 0) from UserCart where userName =?1 and cartID=?2")
	int getItemCount_Cart_RemoveItems(String userName, Long cartID);
	
	
	
	@Query("SELECT Coalesce(SUM(item_price * item_quantity),0) FROM ItemInfo, UserCart WHERE user_name=?1 AND items_itemID = itemID")
	float getCartTotalAmount(String userName);
	
	
	@Query("from UserCart where userName = ?1")
	List<UserCart> getAllItemsInCart(String userName);

	
	@Transactional
	@Modifying
	@Query("DELETE from UserCart where cartID = ?1")
	void deleteOrderedItemsFromCart(Long cartID);
	
}
