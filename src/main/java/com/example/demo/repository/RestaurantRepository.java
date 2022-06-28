package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Restaurants;

public interface RestaurantRepository extends JpaRepository<Restaurants, Long> {

	@Query("SELECT restaurantName from Restaurants")
	List<String> getAllRestaurantNames();

	
	@Query("SELECT restaurantID from Restaurants where restaurantName = ?1")
	Long getRestaurantID(String restaurantName);


	@Query("SELECT restaurantName from Restaurants where restaurantID = ?1")
	String getRestaurantName(Long restaurantID);


	@Query("from Restaurants where restaurantName = ?1")
	Restaurants getRestaurantByName(String restaurantName);
	
	
}
