package com.bodyupbe.bodyupbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BodyUpBeApplication {

	public static void main(String[] args) {

		SpringApplication.run(BodyUpBeApplication.class, args);


	}

}
