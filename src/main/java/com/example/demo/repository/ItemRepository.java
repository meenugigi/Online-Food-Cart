package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ItemInfo;
import com.example.demo.model.Restaurants;

public interface ItemRepository extends JpaRepository<ItemInfo, Long> {

	@Query("from ItemInfo where restaurants_restaurantID = ?1")
	List<ItemInfo> findRestaurantsMenu(Long restaurantID);

	@Transactional
	@Modifying
	@Query("DELETE from ItemInfo where restaurantID = ?1")
	void deleteRestaurantMenu(Long restaurantID);

	@Transactional
	@Modifying
	@Query("UPDATE ItemInfo SET restaurantName=?2 where restaurantID =?1")
	void updateRestaurantName(Long id, String restaurantName);

	

}
