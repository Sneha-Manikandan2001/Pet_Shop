package com.petshop.Sprint_Vaccination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.controller,com.service,com.exception")
@EnableJpaRepositories("com.dao")
@EntityScan("com.model")
public class SprintVaccinationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintVaccinationApplication.class, args);
	}

}
