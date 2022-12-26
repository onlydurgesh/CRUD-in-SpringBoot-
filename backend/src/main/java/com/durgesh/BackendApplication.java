package com.durgesh;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		System.out.println("this is BackEnd Project");
	}
	@Bean 
	ModelMapper mapper()
	{
		return new ModelMapper();
	}
}
