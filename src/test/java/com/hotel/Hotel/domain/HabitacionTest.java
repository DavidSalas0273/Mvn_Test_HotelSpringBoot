package com.hotel.Hotel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HabitacionTest {

    @Test
    @DisplayName("Debe lanzar excepción al intentar asignar una habitación que ya está ocupada")
    void debeLanzarExcepcionAlAsignarHabitacionOcupada() {
        // Arrange
        Habitacion habitacion = new HabitacionEstandar("202", 2, 150.0, 2);
        habitacion.asignarAReserva(); // queda OCUPADA

        // Act & Assert
        assertThrows(
                IllegalStateException.class,
                () -> habitacion.asignarAReserva(),
                "Asignar una habitación ya ocupada debe lanzar IllegalStateException");

        assertEquals(EstadoHabitacion.OCUPADA, habitacion.getEstado());
    }
}
