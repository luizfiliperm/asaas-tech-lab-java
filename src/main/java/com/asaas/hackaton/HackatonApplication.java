package com.asaas.hackaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HackatonApplication {

	public static void main(String[] args) {
        System.out.println("test");
		SpringApplication.run(HackatonApplication.class, args);
	}

}
