package com.example.Ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.YearMonth;

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
		Product airpodpro = new Product();
		airpodpro.setProductId(0);
		airpodpro.setProductName("AirPods Pro");
		airpodpro.setCategory("หูฟัง");
		airpodpro.setDetail("By Apple, Best quality.");
		airpodpro.setPrice(9000);
		productRepository.save(airpodpro);

		Product airpod = new Product();
		airpod.setProductId(1);
		airpod.setProductName("AirPods");
		airpod.setCategory("หูฟัง");
		airpod.setDetail("By Apple, Good quality.");
		airpod.setPrice(6000);
		productRepository.save(airpod);

		Product airForce = new Product();
		airForce.setProductId(2);
		airForce.setProductName("Nike Air Force");
		airForce.setCategory("รองเท้า");
		airForce.setDetail("By NIKE, Best shoes.");
		airForce.setPrice(4000);
		productRepository.save(airForce);
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
