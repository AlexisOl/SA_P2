package com.example.Cines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinesApplication.class, args);
	}

}
