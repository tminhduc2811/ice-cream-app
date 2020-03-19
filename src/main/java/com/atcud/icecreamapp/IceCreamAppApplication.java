package com.atcud.icecreamapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class IceCreamAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(IceCreamAppApplication.class, args);
	}

}
