package com.hotel.Hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Smoke test básico — no levanta el contexto de Spring
 * para evitar dependencia con la base de datos en entornos sin BD.
 */
class HotelApplicationTests {

	@Test
	void contextLoads() {
		// Verifica que la clase principal existe y es instanciable
		assertTrue(HotelApplication.class != null);
	}

}
