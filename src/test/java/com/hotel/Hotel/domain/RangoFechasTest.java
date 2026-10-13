package com.hotel.Hotel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RangoFechasTest {

    // ──────────────────────────────────────────────────────────────
    // Prueba 4: getDias() calcula correctamente la diferencia
    // ──────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe calcular correctamente la cantidad de días entre dos fechas")
    void debeCalcularDiasCorrectamente() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.of(2026, 10, 10, 14, 0);
        LocalDateTime fin    = LocalDateTime.of(2026, 10, 15, 12, 0);

        // Act
        RangoFechas rango = new RangoFechas(inicio, fin);

        // Assert
        assertEquals(4, rango.getDias(),
                "Entre el 10 y el 15 de octubre hay 4 días completos según ChronoUnit.DAYS");
    }

    // ──────────────────────────────────────────────────────────────
    // Prueba 5: fechas invertidas deben lanzar excepción
    // ──────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Debe lanzar excepción cuando la fecha fin es anterior a la fecha inicio")
    void debeLanzarExcepcionConFechasInvertidas() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.of(2026, 10, 15, 14, 0);
        LocalDateTime fin    = LocalDateTime.of(2026, 10, 10, 12, 0); // anterior al inicio

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> new RangoFechas(inicio, fin),
                "Fechas invertidas deben lanzar IllegalArgumentException");
    }
}
