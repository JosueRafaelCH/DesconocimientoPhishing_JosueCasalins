# DECISIONES.md — Registro de Decisiones Técnicas

---

## Decisión #01
- **¿Qué decidí?** Usar `SimiladorService` como único servicio en lugar de servicios separados (`UsuarioService`, `TestService`, `SimulacionService`, `AnalisisAdaptativoService`, `ReporteService`, `EscenarioService`).
- **¿Por qué?** Porque los métodos CRUD de todas las entidades comparten la misma estructura y dependencias (inyección de DAOs). Mantenerlas en un solo servicio reduce la duplicación de código y simplifica la inyección de dependencias en los controllers.
- **¿Qué artefacto respalda esta decisión?** `M9-clases-metodos-CU-RF.md` (matriz actualizada con `SimiladorService` como única clase de servicio), `SimiladorService.java` (212 líneas con todos los métodos).

---

## Decisión #02
- **¿Qué decidí?** Registro público fijo como rol Estudiante; solo el Admin puede crear cuentas Docente o Administrador.
- **¿Por qué?** Por seguridad: evitar que un usuario se auto-asigne un rol con privilegios elevados. El `AuthController.register()` asigna `rol = buscarRolePorId(3)` (Estudiante) de forma fija.
- **¿Qué artefacto respalda esta decisión?** `AuthController.java` línea 57, `M1-entidades-RF-CU-DCA.md`, `M5-entidades-arquetipos-RF-RN.md` RN-01.

---

## Decisión #03
- **¿Qué decidí?** Relación Docente-Estudiante mediante auto-referencia en `Usuario` (`docenteTutor` → `estudiantesTutelados`) en lugar de una entidad separada `Grupo`.
- **¿Por qué?** Porque el CU-07 (Gestionar Grupos) no se implementó y la relación 1:N tutor-estudiante es suficiente para que el Docente consulte métricas de sus estudiantes tutorados. Simplifica el modelo evitando una tabla intermedia.
- **¿Qué artefacto respalda esta decisión?** `Usuario.java` líneas 72-78, `M6-relaciones-MER-cardinalidades.md` fila "Usuarios (docente) tutor de Usuarios (estudiante)".

---

## Decisión #04
- **¿Qué decidí?** Agregar los campos `nivel`, `cantidadPreguntas`, `cantAciertos` y `calificacion` a `TestEvaluativo`, aceptando una violación controlada de 3FN (datos derivados almacenados).
- **¿Por qué?** Porque calcular `cantAciertos` y `calificacion` mediante consultas SQL cada vez que se muestra la vista de resultados o las estadísticas del docente sería ineficiente. Se optó por almacenar el dato calculado en el momento del envío del test (`TestController.enviarTest()` líneas 102-103) para consultas posteriores rápidas. La violación de 3FN es deliberada y está documentada.
- **¿Qué artefacto respalda esta decisión?** `TestEvaluativo.java`, `TestController.java` líneas 102-103, `E10-normalizacion-3FN.md` (documenta la violación de 3FN como decisión).

---

## Decisión #05
- **¿Qué decidí?** El feedback educativo en simulaciones usa texto predefinido hardcodeado en lugar de un motor de IA real.
- **¿Por qué?** Porque implementar un motor de análisis IA requiere integración con servicios externos (OpenAI, modelos locales) y excede el alcance del proyecto. Las entidades `FeedbackIA` y `InteraccionPhishing` están diseñadas para soportar una IA real en el futuro, pero actualmente el mensaje se genera con texto fijo educativo en `SimulacionController.java` líneas 75-80 y 98-103.
- **¿Qué artefacto respalda esta decisión?** `SimulacionController.java`, `FeedbackIA.java`, `M11-estados-eventos-acciones.md`, `M5-entidades-arquetipos-RF-RN.md`.

---

## Decisión #06
- **¿Qué decidí?** Estructurar las templates en subdirectorios por rol (`auth/`, `admin/`, `docente/`, `estudiante/`) en lugar de un solo directorio plano.
- **¿Por qué?** Para mejorar la organización y mantenibilidad: 24 templates organizadas por módulo funcional, facilitando la navegación y evitando confusiones con nombres similares. Se eliminaron 20 archivos duplicados que estaban en la raíz.
- **¿Qué artefacto respalda esta decisión?** Estructura de `src/main/resources/templates/`, `M12-pantallas-roles-CU-RF.md`, `M13-prototipo-wireframes.md`.

---

## Decisión #07
- **¿Qué decidí?** Tema oscuro/claro implementado 100% del lado del cliente con CSS variables + JavaScript + `localStorage`, sin intervención del servidor.
- **¿Por qué?** Porque el cambio de tema es una preferencia de UI que no requiere estado del servidor. `app.js` lee `localStorage` y la preferencia del sistema operativo (`prefers-color-scheme`) para establecer el tema inicial, y `style.css` define las variables de color en `:root` y `[data-theme="dark"]`.
- **¿Qué artefacto respalda esta decisión?** `style.css` líneas 1-37, `app.js` líneas 4-34, `base.html` línea 19-21.

---

## Decisión #08
- **¿Qué decidí?** Envío de test mediante `HttpServletRequest.getParameter()` en lugar de un DTO con `@RequestParam`.
- **¿Por qué?** Porque el test tiene un número variable de preguntas (según el nivel), cada una con un ID dinámico. El controlador itera sobre `preguntaId_1`, `preguntaId_2`, etc. hasta que el parámetro es `null`, lo que permite manejar cualquier cantidad de preguntas sin modificar la firma del método.
- **¿Qué artefacto respalda esta decisión?** `TestController.java` líneas 76-100, `estudiante/preguntas_test.html`.

---

## Decisión #09
- **¿Qué decidí?** Usar Mockito puro con `@ExtendWith(MockitoExtension.class)` para tests en lugar de `@WebMvcTest`/`@MockBean`.
- **¿Por qué?** Porque la versión de Spring Boot (`4.0.6`) tiene un `spring-boot-test-autoconfigure` con solo 22 clases que no incluye `WebMvcTest` ni `MockBean`. Los tests se implementaron con inyección manual de mocks y `MockMvc` standalone hubiera requerido configuración adicional.
- **¿Qué artefacto respalda esta decisión?** `SimiladorServiceTest.java`, `TestControllerTest.java`, `pom.xml`.

---

## Decisión #10
- **¿Qué decidí?** Páginas de error (403, 404, 500) mediante templates en `error/` con resolución automática de Spring Boot, sin `@ControllerAdvice` ni `ErrorController` personalizado.
- **¿Por qué?** Porque Spring Boot `ErrorMvcAutoConfiguration` busca automáticamente `error/<status>.html` en `templates/`. No requiere código adicional. El intento de implementar `ErrorController` falló porque la dependencia `spring-boot-starter-web` original era `spring-boot-starter-webmvc` (artefacto inexistente), y aunque ya se corrigió, las templates funcionan igual sin controller.
- **¿Qué artefacto respalda esta decisión?** `error/403.html`, `error/404.html`, `error/500.html`.

---

## Decisión #11
- **¿Qué decidí?** Cambiar el dominio institucional de `@itm.edu.co` a `@uniremington.edu.co` y el nombre de marca de ITM a Corporación Universitaria Remington.
- **¿Por qué?** Porque el proyecto pertenece a la Corporación Universitaria Remington. Se actualizó en `RegisterRequest.java`, `DataLoader.java`, `auth/login.html`, `auth/register.html`, `base.html`.
- **¿Qué artefacto respalda esta decisión?** `RegisterRequest.java` línea 17, `base.html` líneas 16 y 44, `08-RF y RNF.md` (el requisito original mencionaba `@itm.edu.co` pero se acordó el cambio).

---

## Decisión #12
- **¿Qué decidí?** Agregar el campo `pregunta` (ManyToOne → PreguntaTest) a `RespuestaTest`.
- **¿Por qué?** Porque la vista de resultados del test necesita mostrar el enunciado de cada pregunta junto con la respuesta del usuario. Sin este campo, sería necesario un JOIN adicional en cada consulta. El diseño original de `Respuestas_Test` tenía `id_opcion` como FK, pero recuperar la pregunta asociada requería navegar `Opcion → Pregunta`. Tener la FK directa simplifica las consultas.
- **¿Qué artefacto respalda esta decisión?** `RespuestaTest.java`, `TestController.java` línea 93, `estudiante/test_resultados.html`.

---

## Decisión #13
- **¿Qué decidí?** Mantener `spring.jmx.enabled=false` en `application.properties`.
- **¿Por qué?** Porque JMX (Java Management Extensions) no es necesario para la aplicación y causaba advertencias no bloqueantes en consola durante el desarrollo con VS Code. Deshabilitarlo elimina el ruido en los logs.
- **¿Qué artefacto respalda esta decisión?** `application.properties` línea 16.

---

## Decisión #14
- **¿Qué decidí?** Corregir `pom.xml`: reemplazar `spring-boot-starter-webmvc` por `spring-boot-starter-web`, y reemplazar 5 dependencias de test inexistentes por `spring-boot-starter-test`.
- **¿Por qué?** Porque los artefactos `*-actuator-test`, `*-data-jpa-test`, `*-thymeleaf-test`, `*-validation-test` y `*-webmvc-test` no existen en Maven Central. `spring-boot-starter-webmvc` tampoco existe (el correcto es `spring-boot-starter-web`). Estas dependencias inválidas impedían la compilación de tests y generaban errores de resolución.
- **¿Qué artefacto respalda esta decisión?** `pom.xml`, salida de `mvn dependency:tree`.

---

## Decisión #15
- **¿Qué decidí?** Restringir `/docente/**` con `hasRole("DOCENTE")` en `SecurityConfig`.
- **¿Por qué?** Porque inicialmente la única protección por rol era para `/admin/**`. Los endpoints de docente quedaban accesibles para cualquier usuario autenticado, incluyendo estudiantes. Se agregó la línea `.requestMatchers("/docente/**").hasRole("DOCENTE")` para cerrar esa brecha.
- **¿Qué artefacto respalda esta decisión?** `SecurityConfig.java` línea 20, auditoría de seguridad.

---

## Decisión #16
- **¿Qué decidí?** Actualizar las 13 matrices de trazabilidad para reflejar el estado real del código.
- **¿Por qué?** Porque las matrices originales hacían referencia a clases, servicios y flujos que no existían en la implementación (ej: `UsuarioService`, `TestService`, `Campaña`). Se corrigieron para que cada matriz sea coherente con el código sin perder la trazabilidad con los documentos de requisitos.
- **¿Qué artefacto respalda esta decisión?** `docs/trazabilidad/M1.md` a `M13.md`, auditoría de trazabilidad.

---

## Decisión #17
- **¿Qué decidí?** No implementar entidad `Grupo` ni `Reporte`, y dejar sin implementar los CU-04 (completo), CU-07, RF03, RF06, RF08.
- **¿Por qué?** Por limitación de alcance del proyecto. Se priorizó la funcionalidad core: autenticación, test diagnóstico, CRUD admin, estadísticas docente y simulación básica con feedback educativo. Las funcionalidades restantes quedan documentadas en las matrices como "no implementadas" para trazabilidad.
- **¿Qué artefacto respalda esta decisión?** Auditorías de CU, matrices M1-M13 (reflejan el estado real), `docs/analisis/07-casos-de-uso.md` y `08-RF y RNF.md` (requisitos originales).
