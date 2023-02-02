package com.eu.taxcalculation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TaxcalculationApplication {

	public static void main(String[] args) {
		SpringApplication. run(TaxcalculationApplication.class, args);
		System.out.println("It's UP");
	}

}
