package com.springboot.forent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ForentApplication {
	public static void main(String[] args) {
		SpringApplication.run(ForentApplication.class, args);
	}

}