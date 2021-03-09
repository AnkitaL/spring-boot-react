
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class SpringBootWithReactApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithReactApplication.class, args);
	}
	
	@Autowired
	private UserRepository UserRepository;

	@Override
	public void run(String... args) throws Exception {
		this.UserRepository.save(new User("ankita","lakhani","lakhaniankita001@gmail.com"));
		this.UserRepository.save(new User("dhruv","sheladia","dhruvsheladia@gmail.com"));
		this.UserRepository.save(new User("sanket","lakhani","sanketlakhani@yahoo.com"));
		
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/generate-config").allowedMethods("*").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
