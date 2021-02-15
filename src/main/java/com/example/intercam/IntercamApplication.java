package com.example.intercam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IntercamApplication  {


	public static void main(String[] args) {
		SpringApplication.run(IntercamApplication.class, args);
	}

}
