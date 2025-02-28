package com.kanishka.esd_final_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EsdFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsdFinalProjectApplication.class, args);
	}

}
