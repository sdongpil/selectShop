package com.sparta.myselectshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing  // JPA Auditing 활성화
@EnableScheduling
public class MyselectshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyselectshopApplication.class, args);
	}

}
