package com.example.demo;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.controller.CartController;

@SpringBootApplication
public class OnlineFoodCartApplication {

	public static void main(String[] args) {
		
		new File(CartController.uploadDirectory).mkdir();
		SpringApplication.run(OnlineFoodCartApplication.class, args);
	}

}
