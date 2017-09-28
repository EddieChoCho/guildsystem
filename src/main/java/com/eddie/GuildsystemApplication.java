package com.eddie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.eddie.repository"})
public class GuildsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuildsystemApplication.class, args);
	}
}
