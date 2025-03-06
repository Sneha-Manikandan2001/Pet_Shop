package com.petshop.Sprint_PetsCategory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.controller,com.service,com.exception")
@EnableJpaRepositories("com.dao")
@EntityScan("com.model")

public class SprintPetsCategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintPetsCategoryApplication.class, args);
	}

}
