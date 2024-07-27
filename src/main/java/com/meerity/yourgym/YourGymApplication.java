package com.meerity.yourgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.meerity.yourgym.model")
@EnableJpaRepositories(basePackages = "com.meerity.yourgym.repositories")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class YourGymApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourGymApplication.class, args);
	}

}
