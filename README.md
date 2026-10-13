# Pruebas Unitarias - HotelSpringBoot

Este repositorio contiene las pruebas unitarias desarrolladas a partir del proyecto HotelSpringBoot,
un sistema de reservas de hotel construido con Spring Boot 3.3.4 y Java 17.

Las pruebas cubren la capa de dominio del sistema y verifican el comportamiento de las clases
principales sin necesidad de levantar el contexto de Spring ni conectarse a una base de datos.

---

## Origen del proyecto base

Las pruebas fueron escritas tomando como base el proyecto HotelSpringBoot, que implementa una
arquitectura REST por capas con las siguientes entidades principales: Cliente, Habitacion, Reserva
y RangoFechas. El proyecto original usa PostgreSQL como base de datos y MapStruct para el mapeo
entre entidades y DTOs.

---

## Archivos de prueba

### ClienteTest.java

Ubicacion: src/test/java/com/hotel/Hotel/domain/ClienteTest.java

Contiene 3 pruebas sobre el comportamiento del dominio de la clase Cliente:

**Prueba 1 - debeDesactivarClienteConTresPenalizaciones**
Verifica que al registrar 3 penalizaciones consecutivas sobre un cliente, este queda marcado como
inactivo y no puede realizar reservas. La logica de negocio establece que acumular 3 penalizaciones
desactiva la cuenta automaticamente.

**Prueba 2 - debeReactivarClienteYLimpiarPenalizaciones**
Verifica que al llamar al metodo reactivar() sobre un cliente inactivo, este vuelve a estar activo
y su contador de penalizaciones se reinicia a cero. Se parte de un cliente que ya tenia 3
penalizaciones acumuladas.

**Prueba 3 - debeLanzarExcepcionConNombreVacio**
Verifica que el constructor de Cliente lanza una IllegalArgumentException cuando se intenta crear
un cliente con nombre vacio o en blanco. Garantiza que las validaciones del dominio se aplican
en el momento de construccion del objeto.

---

### RangoFechasTest.java

Ubicacion: src/test/java/com/hotel/Hotel/domain/RangoFechasTest.java

Contiene 2 pruebas sobre el value object RangoFechas, que representa el periodo de una reserva:

**Prueba 4 - debeCalcularDiasCorrectamente**
Verifica que el metodo getDias() calcula correctamente la diferencia en dias entre la fecha de
inicio y la fecha de fin usando ChronoUnit.DAYS. En el caso de prueba se usan fechas con diferencia
de 4 dias completos (del 10 al 15 de octubre).

**Prueba 5 - debeLanzarExcepcionConFechasInvertidas**
Verifica que el constructor de RangoFechas lanza una IllegalArgumentException cuando la fecha de
fin es anterior a la fecha de inicio. Esta validacion evita que se creen periodos logicamente
invalidos en el sistema.

---

## Tecnologias utilizadas

- Java 17
- JUnit 5 (Jupiter)
- Spring Boot Test 3.3.4
- Maven Wrapper (mvnw)

## Ejecutar las pruebas

```
.\mvnw.cmd test
```

Resultado esperado: Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
