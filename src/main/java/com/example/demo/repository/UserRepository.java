package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.*;
import org.springframework.stereotype.*;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	
	@Query("SELECT contact from User where email = ?1")
	String getUserContactInfo(String userName);

}
