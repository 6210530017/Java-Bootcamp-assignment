package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void addInitialUser() {
		User user = new User();
		user.setName("Pramote");
		user.setAddress("Nakhon Sawan, Thailand. 60000");
		userRepository.save(user);
	}

	@PostConstruct
	public void addInitialProduct() {
		Product airpod = new Product();
		airpod.setProductName("AirPods Pro");
		airpod.setCategory("หูฟัง");
		airpod.setDetail("By Apple, Good quality.");
		airpod.setPrice(9000);
		productRepository.save(airpod);
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
