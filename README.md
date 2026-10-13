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

## Como correr las pruebas

Desde Git Bash, estando en la carpeta del proyecto HotelSpringBoot:

```bash
JAVA_HOME="C:/Program Files/Java/jdk-17" ./mvnw.cmd test
```

Resultado esperado:

```
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## Archivos de prueba

### ClienteTest.java

Ubicacion: src/test/java/com/hotel/Hotel/domain/ClienteTest.java

Contiene 4 pruebas sobre el comportamiento de la clase Cliente:

**Prueba 1 - debeDesactivarClienteConTresPenalizaciones**
Verifica que al registrar 3 penalizaciones consecutivas sobre un cliente, este queda marcado como
inactivo y no puede realizar reservas. La logica de negocio establece que acumular 3 penalizaciones
desactiva la cuenta automaticamente. Se verifica que isActivo() retorne false y que
puedeRealizarReservas() tambien retorne false.

**Prueba 2 - debeReactivarClienteYLimpiarPenalizaciones**
Verifica que al llamar al metodo reactivar() sobre un cliente inactivo, este vuelve a estar activo
y su contador de penalizaciones se reinicia a cero. Se parte de un cliente que ya tenia 3
penalizaciones acumuladas, es decir, ya estaba bloqueado.

**Prueba 3 - debeLanzarExcepcionConNombreVacio**
Verifica que el constructor de Cliente lanza una IllegalArgumentException cuando se intenta crear
un cliente con nombre vacio o en blanco. La validacion ocurre en el dominio, no en la capa web,
lo que garantiza que nunca exista un cliente sin nombre en el sistema.

**Prueba 4 - dosPenalizacionesNoDebenDesactivarCliente**
Verifica que con solo 2 penalizaciones el cliente sigue activo y puede realizar reservas. Complementa
la prueba 1 confirmando que el umbral de desactivacion es exactamente 3, no antes. Esto evita
que el sistema bloquee clientes por error antes de tiempo.

---

### HabitacionTest.java

Ubicacion: src/test/java/com/hotel/Hotel/domain/HabitacionTest.java

Contiene 1 prueba sobre el comportamiento de la clase Habitacion:

**Prueba 5 - debeLanzarExcepcionAlAsignarHabitacionOcupada**
Verifica que si una habitacion ya esta en estado OCUPADA y se intenta asignarla nuevamente a otra
reserva, el sistema lanza una IllegalStateException. Esto protege al sistema de dobles reservas
sobre la misma habitacion. Se crea una habitacion, se asigna una vez para dejarla ocupada, y luego
se intenta asignar de nuevo esperando la excepcion.

---

### RangoFechasTest.java

Ubicacion: src/test/java/com/hotel/Hotel/domain/RangoFechasTest.java

Contiene 2 pruebas sobre el value object RangoFechas, que representa el periodo de una reserva:

**Prueba 6 - debeCalcularDiasCorrectamente**
Verifica que el metodo getDias() calcula correctamente la diferencia en dias entre la fecha de
inicio y la fecha de fin usando ChronoUnit.DAYS. En el caso de prueba se usan fechas con diferencia
de 4 dias completos (del 10 al 15 de octubre). Este calculo es critico porque el costo total de
la reserva depende directamente de el.

**Prueba 7 - debeLanzarExcepcionConFechasInvertidas**
Verifica que el constructor de RangoFechas lanza una IllegalArgumentException cuando la fecha de
fin es anterior a la fecha de inicio. Esta validacion evita que se creen periodos logicamente
invalidos en el sistema, como una reserva que termina antes de empezar.

---

## Tecnologias utilizadas

- Java 17
- JUnit 5 (Jupiter)
- Spring Boot Test 3.3.4
- Maven Wrapper (mvnw)

---

## Notas

Ninguno de los archivos en src/main fue modificado. Todas las pruebas operan unicamente sobre
la capa de dominio y no requieren conexion a base de datos ni contexto de Spring activo.
