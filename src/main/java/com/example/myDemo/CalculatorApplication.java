package com.example.myDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.myDemo.model")
@EnableJpaRepositories(basePackages = "com.example.myDemo.repository")
@ComponentScan(basePackages = "com.example.myDemo")
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
		System.out.println("Hi! This is a simple calculator program");
	}

}

