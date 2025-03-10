package com.example.SafetyNet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.SafetyNet")
public class SafetyNetAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

}
