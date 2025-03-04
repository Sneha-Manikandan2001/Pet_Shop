package com.petshop.Sprint_Suppliers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.controller,com.service,com.exception")
@EntityScan("com.model")
@EnableJpaRepositories("com.dao")
public class SprintSuppliersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintSuppliersApplication.class, args);
	}

}
