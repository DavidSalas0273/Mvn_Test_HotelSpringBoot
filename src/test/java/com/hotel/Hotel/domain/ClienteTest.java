package com.hotel.Hotel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    // ──────────────────────────────────────────────────────────────
    // Prueba 1: tres penalizaciones desactivan al cliente
    // ──────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe desactivar al cliente al acumular 3 penalizaciones")
    void debeDesactivarClienteConTresPenalizaciones() {
        // Arrange
        Cliente cliente = new Cliente("Ana López", "ana@hotel.com");
        assertTrue(cliente.isActivo(), "El cliente debe iniciar activo");

        // Act
        cliente.registrarPenalizacion(); // 1
        cliente.registrarPenalizacion(); // 2
        cliente.registrarPenalizacion(); // 3 → desactiva

        // Assert
        assertFalse(cliente.isActivo(), "El cliente debe quedar inactivo tras 3 penalizaciones");
        assertEquals(3, cliente.getPenalizaciones());
        assertFalse(cliente.puedeRealizarReservas());
    }

    // ──────────────────────────────────────────────────────────────
    // Prueba 2: reactivar restablece estado y penalizaciones
    // ──────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe reactivar al cliente y limpiar sus penalizaciones")
    void debeReactivarClienteYLimpiarPenalizaciones() {
        // Arrange
        Cliente cliente = new Cliente("Luis Ramírez", "luis@hotel.com");
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion(); // queda inactivo

        // Act
        cliente.reactivar();

        // Assert
        assertTrue(cliente.isActivo(), "El cliente debe estar activo después de reactivar");
        assertEquals(0, cliente.getPenalizaciones(), "Las penalizaciones deben reiniciarse a 0");
        assertTrue(cliente.puedeRealizarReservas());
    }

    // ──────────────────────────────────────────────────────────────
    // Prueba 3: constructor rechaza nombre y email en blanco
    // ──────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe lanzar excepción al crear cliente con nombre vacío")
    void debeLanzarExcepcionConNombreVacio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente("", "correo@hotel.com"),
                "Nombre vacío debe lanzar IllegalArgumentException");
    }

    // ──────────────────────────────────────────────────────────────
    // Prueba 6: dos penalizaciones no deben desactivar al cliente
    // ──────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Dos penalizaciones no deben desactivar al cliente")
    void dosPenalizacionesNoDebenDesactivarCliente() {
        // Arrange
        Cliente cliente = new Cliente("Mario Torres", "mario@hotel.com");

        // Act
        cliente.registrarPenalizacion(); // 1
        cliente.registrarPenalizacion(); // 2

        // Assert
        assertTrue(cliente.isActivo(),
                "Con solo 2 penalizaciones el cliente debe seguir activo");
        assertEquals(2, cliente.getPenalizaciones());
        assertTrue(cliente.puedeRealizarReservas());
    }
}
