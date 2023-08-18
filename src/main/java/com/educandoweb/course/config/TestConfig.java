package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		Order o1 = new Order(null, Instant.parse("2020-07-18T17:03:54Z"), OrderStatus.SHIPPED, u1);
		Order o2 = new Order(null, Instant.parse("2021-08-18T18:04:55Z"), OrderStatus.PAID, u2);
		Order o3 = new Order(null, Instant.parse("2022-09-18T19:05:56Z"), OrderStatus.WAITING_PAYMENT, u1);

		Category c1 = new Category(null, "Brinquedos");
		Category c2 = new Category(null, "Comidas");
		Category c3 = new Category(null, "Eletrônicos");
		userRepository.saveAll(Arrays.asList(u1, u2));

		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
}
