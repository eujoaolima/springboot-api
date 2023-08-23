package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import com.educandoweb.course.entities.*;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

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

		Product p1 = new Product(null, "Batata", "Pacote de batatas", 10.00, "batata.png");
		Product p2 = new Product(null, "S23+ 512gb", "Celular brabo", 4500.00, "batata.png");
		Product p3 = new Product(null, "Carrinho de controle remoto", "Carrinho de controle remoto", 249.97, "batata.png");

		userRepository.saveAll(Arrays.asList(u1, u2));

		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));

		p1.getCategories().add(c2);
		p2.getCategories().add(c3);
		p3.getCategories().add(c1);
		p3.getCategories().add(c3);

		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		OrderItem oi1 = new OrderItem(o1, p1, 100, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p2, 5, p2.getPrice());

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));


		Payment pay1 = new Payment(null, Instant.parse("2020-07-20T21:53:12Z"), o1);
		o1.setPayment(pay1);

		orderRepository.save(o1);
	}
}
