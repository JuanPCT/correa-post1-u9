Repositorio GitHub: https://github.com/JuanPCT/correa-post1-u9

# Unidad 9 — Recordatorios de citas

[![Pruebas Maven](https://github.com/JuanPCT/correa-post1-u9/actions/workflows/ci.yml/badge.svg)](https://github.com/JuanPCT/correa-post1-u9/actions/workflows/ci.yml)

Servicio Spring Boot 3 y Java 21 que consulta citas dentro de las próximas 24 horas y envía el recordatorio por el canal elegido. Una Factory selecciona las estrategias de correo y SMS; `Clock` permite fijar el tiempo en pruebas.

## Ejecutar

```bash
mvn clean verify
mvn spring-boot:run
```

H2 se crea en memoria. Los gateways de esta práctica registran mensajes en consola; no contactan proveedores ni envían comunicaciones reales.

## Pruebas

- `CitaReminderServiceImplTest`: ventana temporal, canal seleccionado, lista vacía y fallo de envío sin persistencia.
- `NotificadorUrgenteTest`: envío correcto, fallo del gateway y cita inexistente.
- `CitaRepositoryTest` (`@DataJpaTest`): consulta JPA/H2 por rango y estado.
- `RecordatorioIntegrationTest` (`@SpringBootTest`): Spring, JPA, Factory y estrategia SMS reales; solo el gateway externo se sustituye.
- JaCoCo genera HTML y XML en `target/site/jacoco/` durante `verify`.

## Decisiones

1. Un `Clock` fijo controla tiempo; mocks aíslan la orquestación; H2 verifica consultas; gateway sustituido en integración evita red.
2. El caso de uso recibe repositorio, Factory y reloj. Cada canal depende de un gateway intercambiable.
3. La configuración publica `UrgentGateway` con qualifier. La cita se marca solo después de un envío correcto.
4. En producción, una falla entre el envío y el commit puede duplicar un mensaje al reintentar. Se mitigaría con idempotencia y outbox.

El workflow `.github/workflows/ci.yml` ejecuta `mvn -B verify` con Temurin 21 en cada push y pull request.

Antes de aplicar DIP, construir el gateway con `new` dentro de `NotificadorUrgente` habría obligado a usar el transporte concreto para probar el fallo. Ahora la configuración construye el canal y el servicio recibe el contrato por constructor. La prueba configura un gateway que falla y comprueba que el repositorio no guarda la cita como enviada. Strategy permite verificar solo el canal elegido, mientras Factory aísla la prueba de cómo se registran y seleccionan los canales.

[Ejecución de CI con las ocho pruebas](https://github.com/JuanPCT/correa-post1-u9/actions/runs/44752027166).
