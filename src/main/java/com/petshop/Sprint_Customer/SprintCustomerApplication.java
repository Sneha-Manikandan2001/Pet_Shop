package com.petshop.Sprint_Customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.controller,com.service,com.globalexecption")
@EntityScan("com.model")
@EnableJpaRepositories("com.dao")
public class SprintCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintCustomerApplication.class, args);
	}

}
