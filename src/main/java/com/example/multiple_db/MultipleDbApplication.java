package com.example.multiple_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.multiple_db.order", "com.example.multiple_db.product"})
//@ComponentScan(basePackages = {"com.example.multiple_db.order", "com.example.multiple_db.product"})
public class MultipleDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDbApplication.class, args);
	}

}
