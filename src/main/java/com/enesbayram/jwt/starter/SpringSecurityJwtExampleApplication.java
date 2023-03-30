package com.enesbayram.jwt.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.enesbayram"})
@EntityScan(basePackages = {"com.enesbayram"})
@EnableJpaRepositories(basePackages = {"com.enesbayram"})
@SpringBootApplication
public class SpringSecurityJwtExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);
	}

}
