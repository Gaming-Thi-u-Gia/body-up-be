package com.bodyupbe.bodyupbe;

import com.bodyupbe.bodyupbe.dto.request.community.BadgeDto;
import com.bodyupbe.bodyupbe.model.community.Badge;
import com.bodyupbe.bodyupbe.model.user.DemoUser;
import com.bodyupbe.bodyupbe.repository.BadgeRepository;
import com.bodyupbe.bodyupbe.service.InitializeDataImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BodyUpBeApplication {

	public static void main(String[] args) {

		SpringApplication.run(BodyUpBeApplication.class, args);


	}

}
