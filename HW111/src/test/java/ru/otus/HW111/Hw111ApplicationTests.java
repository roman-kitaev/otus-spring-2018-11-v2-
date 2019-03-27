package ru.otus.HW111;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Hw111ApplicationTests {

	@Autowired
	private RouterFunction route;

	@Test
	public void testRoute() {
		WebTestClient client = WebTestClient
				.bindToRouterFunction(route)
				.build();

		client.post()
				.uri("/func/books/create")
				.exchange()
				.expectStatus()
				.isOk();

		client.get()
				.uri("/func/books")
				.exchange()
				.expectStatus()
				.isOk();
	}

}
