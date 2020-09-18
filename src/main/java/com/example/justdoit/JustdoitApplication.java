package com.example.justdoit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class JustdoitApplication {
	public static void main(String[] args) {
		SpringApplication.run(JustdoitApplication.class, args);
	}
	@RequestMapping("/")
	public String greetings(){
		return "<h1>Spring Boot Rocks!</h1>";
	}
}
