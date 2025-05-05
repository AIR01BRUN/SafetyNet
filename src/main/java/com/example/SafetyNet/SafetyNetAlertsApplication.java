package com.example.safetynet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.safetynet")
public class SafetyNetAlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

}
