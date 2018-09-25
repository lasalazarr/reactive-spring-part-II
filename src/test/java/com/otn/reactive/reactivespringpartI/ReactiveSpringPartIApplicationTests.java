package com.otn.reactive.reactivespringpartI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReactiveSpringPartIApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp() {
		webClient = webClient
				.mutate()
				.responseTimeout(Duration.ofSeconds(300))
				.build();
	}

	@Test
	public void testObtenerListaClietes() {
		byte[] resultado = webClient
				.get().uri("/reactive/v1/cliente")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody().returnResult().getResponseBody();
		System.out.format("%s: %s%n", "La lista de clientes:", new String(resultado));
	}
	@Test
	public void testValidarListaClientes() {

		webClient
				.get().uri("/reactive/v1/cliente")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("[{\"id\":\"1005\",\"nombres\":\"Tamara\",\"apellidos\":\"Cisneros\",\"telefono\":4356772,\"direccion\":\"Quito, EC876590\"},{\"id\":\"1004\",\"nombres\":\"Paola\",\"apellidos\":\"Llanos\",\"telefono\":87878799,\"direccion\":\"Quito, EC179087\"},{\"id\":\"1003\",\"nombres\":\"Maria\",\"apellidos\":\"Valdez\",\"telefono\":82923233,\"direccion\":\"Quito, EC178976\"},{\"id\":\"1002\",\"nombres\":\"Pablo\",\"apellidos\":\"Arizaga\",\"telefono\":22332233,\"direccion\":\"Quito, EC170104\"},{\"id\":\"1001\",\"nombres\":\"Alberto\",\"apellidos\":\"Salazar\",\"telefono\":3299999,\"direccion\":\"Quito, EC170102\"}]");
	}
}
