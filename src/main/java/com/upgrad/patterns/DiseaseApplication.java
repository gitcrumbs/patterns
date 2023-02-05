package com.upgrad.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DiseaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiseaseApplication.class, args);
	}


	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();

	}


}
