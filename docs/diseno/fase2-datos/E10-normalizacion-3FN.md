## Modelo Inicial Sin Normalizar

El punto de partida fue una única tabla plana que intentaba capturar toda la información del sistema en un solo lugar. Este tipo de diseño genera múltiples problemas de redundancia y es incompatible con las formas normales.

### Tabla plana inicial: Sistema_Phishing_Plano

| Campo | Tipo | Ejemplo | Problema |
| :--- | :--- | :--- | :--- |
| id_usuario | INT | 1 | — |
| nombre_usuario | VARCHAR | Juan Pérez | — |
| correo | VARCHAR | juan@uni.edu.co | — |
| rol | VARCHAR | Estudiante | Texto repetido en cada fila |
| estado_usuario | VARCHAR | Activo | Texto repetido en cada fila |
| estrato | INT | 3 | Sin validación de rango 1-6 |
| nombre_nivel | VARCHAR | Fácil | Repetido por cada test del usuario |
| puntaje_test | DECIMAL | 4.5 | Depende del nivel, no del usuario |
| id_nivel_asignado | INT | 1 | Depende del puntaje, no del test |
| titulo_escenario | VARCHAR | Correo falso banco | Repetido por cada evento |
| opcion_1_pregunta | VARCHAR | Respuesta A | Viola 1FN: columnas repetidas |
| opcion_2_pregunta | VARCHAR | Respuesta B | Viola 1FN: columnas repetidas |
| opcion_3_pregunta | VARCHAR | Respuesta C | Viola 1FN: columnas repetidas |
| respuesta_usuario | VARCHAR | Respuesta A | Duplica texto de la opción elegida |
| feedback_ia | TEXT | Cuidado con... | Mezclado con datos de interacción |

### Problemas identificados en el modelo plano
* ✗ Datos de rol y estado repetidos en cada fila de usuario.
* ✗ Opciones de respuesta como columnas separadas (opcion_1, opcion_2, opcion_3).
* ✗ El texto de la opción elegida duplica el texto almacenado en la opción.
* ✗ El nivel asignado depende del puntaje, no directamente del test (dependencia transitiva).
* ✗ El feedback de IA mezclado en la misma fila que los datos de comportamiento.
* ✗ Sin restricción de rango para el campo estrato.

---

## 3. Primera Forma Normal (1FN)

### Definición de 1FN
Una tabla está en Primera Forma Normal cuando:
1. Todos los atributos contienen valores atómicos (indivisibles).
2. No existen grupos repetidos de columnas.
3. Cada fila es única e identificable por una clave primaria.

### Violación detectada: Opciones de respuesta como columnas repetidas
La tabla plana contenía los campos `opcion_1_pregunta`, `opcion_2_pregunta` y `opcion_3_pregunta`. Esto viola la 1FN porque agrupa datos del mismo tipo en columnas separadas. Si en el futuro una pregunta tiene 4 opciones, habría que modificar la estructura de la tabla.

> ❌ **Antes de aplicar 1FN (viola la norma)**
> `Preguntas_Test { id_pregunta, enunciado, id_nivel, opcion_1, opcion_2, opcion_3, correcta }`
> → **Problema:** Las opciones son un grupo repetido. No se puede agregar una 4ª opción sin alterar el esquema.

> ✓ **Después de aplicar 1FN (cumple la norma)**
> `Preguntas_Test { id_pregunta, enunciado, id_nivel, fecha_actualizacion }`
> `Opciones_Pregunta { id_opcion, id_pregunta (FK), texto_opcion VARCHAR(500), es_correcta BOOLEAN }`
> → **Solución:** Cada opción ocupa su propia fila. Se pueden agregar N opciones sin cambiar el esquema.

### Violación detectada: respuesta_usuario duplica texto_opcion
El campo `respuesta_usuario` en `Respuestas_Test` almacenaba el texto de la opción elegida por el usuario. Sin embargo, ese mismo texto ya existe en `Opciones_Pregunta.texto_opcion`. Guardar el mismo dato en dos lugares es una violación al principio de atomicidad y fuente de inconsistencias.

> ❌ **Antes (dato duplicado)**
> `Respuestas_Test { id_respuesta, id_test, id_pregunta, id_opcion, respuesta_usuario }`
> → `respuesta_usuario` = 'Respuesta A' (mismo texto que `Opciones_Pregunta.texto_opcion`).

> ✓ **Después (sin duplicación)**
> `Respuestas_Test { id_respuesta, id_test, id_pregunta, id_opcion (FK) }`
> → El texto se obtiene: `SELECT op.texto_opcion FROM Respuestas_Test rt`
> → `JOIN Opciones_Pregunta op ON rt.id_opcion = op.id_opcion`

### Resultado tras aplicar 1FN

| Tabla | Estado tras 1FN |
| :--- | :--- |
| Preguntas_Test | ✓ Opciones separadas en Opciones_Pregunta |
| Opciones_Pregunta | ✓ Nueva tabla — una fila por opción |
| Respuestas_Test | ✓ Eliminado respuesta_usuario — se accede vía JOIN |
| Resto de tablas | ✓ Ya tenían valores atómicos — sin cambios |

---

## 4. Segunda Forma Normal (2FN)

### Definición de 2FN
Una tabla está en Segunda Forma Normal cuando:
1. Está en 1FN.
2. Todos los atributos no clave dependen completamente de la clave primaria.
3. No existen dependencias parciales (atributos que dependen solo de parte de una clave compuesta).

*Nota: La 2FN solo aplica a tablas con claves primarias compuestas.*

En el modelo del sistema todas las tablas utilizan claves primarias simples (un único campo auto-incremental como `id_usuario`, `id_test`, etc.). Por esta razón, no existen dependencias parciales posibles y todas las tablas cumplen automáticamente la Segunda Forma Normal una vez que cumplen la 1FN.

> ✓ **2FN cumplida automáticamente**
> * Todas las tablas usan PK simple (id auto-incremental).
> * Ningún atributo depende de una fracción de la clave — dependen de ella completa.
> * No fue necesario realizar ningún cambio estructural para cumplir 2FN.

### Verificación por tabla

| Tabla | Tipo de PK | Estado 2FN |
| :--- | :--- | :--- |
| Roles | Simple (id_rol) | ✓ Cumple — PK simple |
| Estados_Usuario | Simple (id_estado) | ✓ Cumple — PK simple |
| Estados_Evento | Simple (id_estado_evento) | ✓ Cumple — PK simple |
| Estratos | Simple (id_estrato) | ✓ Cumple — PK simple |
| Usuarios | Simple (id_usuario) | ✓ Cumple — PK simple |
| Niveles_Dificultad | Simple (id_nivel) | ✓ Cumple — PK simple |
| Test_Evaluativo | Simple (id_test) | ✓ Cumple — PK simple |
| Preguntas_Test | Simple (id_pregunta) | ✓ Cumple — PK simple |
| Opciones_Pregunta | Simple (id_opcion) | ✓ Cumple — PK simple |
| Respuestas_Test | Simple (id_respuesta) | ✓ Cumple — PK simple |
| Escenarios_Phishing | Simple (id_escenario) | ✓ Cumple — PK simple |
| Eventos_Simulacion | Simple (id_evento) | ✓ Cumple — PK simple |
| Interacciones_Phishing | Simple (id_interaccion) | ✓ Cumple — PK simple |
| Feedback_IA | Simple (id_feedback) | ✓ Cumple — PK simple |

---

## 5. Tercera Forma Normal (3FN)

### Definición de 3FN
Una tabla está en Tercera Forma Normal cuando:
1. Está en 2FN.
2. No existen dependencias transitivas: ningún atributo no clave depende de otro atributo no clave.
*La regla es: X → Y → Z está prohibido si Y no es clave.*

### Violación #1 — Dependencia transitiva en Test_Evaluativo
En el modelo inicial, la tabla `Test_Evaluativo` contenía el campo `id_nivel_asignado`. El nivel asignado no depende directamente del test, sino del puntaje obtenido en el test. Esto crea una dependencia transitiva:

> **Cadena de dependencia transitiva detectada**
> `id_test` → `puntaje_final` → `id_nivel_asignado`

Esto viola 3FN porque `id_nivel_asignado` depende de `puntaje_final` (un atributo no clave), no directamente de `id_test` (la clave primaria).

> ❌ **Antes de aplicar 3FN (viola la norma)**
> `Test_Evaluativo { id_test (PK), id_usuario, fecha_realizacion, puntaje_final, id_nivel_asignado }`
> → `id_nivel_asignado` depende de `puntaje_final`, no de `id_test`.
> → Si el puntaje se recalcula, `id_nivel_asignado` queda inconsistente.

> ✓ **Después de aplicar 3FN**
> `Test_Evaluativo { id_test (PK), id_usuario, fecha_realizacion, fecha_actualizacion }`
> `Niveles_Dificultad { id_nivel (PK), nombre_nivel, puntaje_minimo, puntaje_maximo, descripcion }`
> → El puntaje se calcula en la aplicación con: `SUM(op.es_correcta)` sobre `Respuestas_Test`.
> → El nivel se determina cruzando el puntaje con los rangos de `Niveles_Dificultad`.
> → No se almacena ninguna dependencia transitiva.

### Violación #2 — puntaje_final como dato calculado
El campo `puntaje_final` en `Test_Evaluativo` era un dato derivado: su valor se calcula contando las respuestas correctas del usuario en `Respuestas_Test`. Almacenarlo genera el riesgo de que el valor calculado y las respuestas reales queden inconsistentes.

> ❌ **Antes (dato derivado almacenado)**
> `Test_Evaluativo { ..., puntaje_final DECIMAL }`
> → Si se agrega o edita una `Respuesta_Test`, `puntaje_final` queda desactualizado.
> → El frontend podría enviar un puntaje manipulado.

> ✓ **Después (calculado dinámicamente)**
> `Test_Evaluativo { id_test, id_usuario, fecha_realizacion, fecha_actualizacion }` ← sin puntaje_final
> → Consulta para obtener el puntaje:
> → `SELECT COUNT(*) FROM Respuestas_Test rt`
> → `JOIN Opciones_Pregunta op ON rt.id_opcion = op.id_opcion`
> → `WHERE rt.id_test = ? AND op.es_correcta = TRUE`

### Violación #3 — Atributos descriptivos repetidos sin catálogo
Los campos `rol`, `estado_usuario` y `estrato` se almacenaban como texto libre en la tabla `Usuarios`, generando el mismo valor repetido en cada fila. Esto viola 3FN porque esos atributos dependen de un identificador propio, no del `id_usuario`.

> ❌ **Antes (texto repetido)**
> `Usuarios { ..., rol VARCHAR, estado_usuario VARCHAR, estrato INT }`
> → 'Estudiante' repetido en 500 filas — si cambia el nombre, hay que actualizar 500 registros.
> → `estrato` sin restricción: se podría insertar estrato = 99.

> ✓ **Después (catálogos normalizados)**
> `Roles { id_rol (PK), nombre_rol }`
> `Estados_Usuario { id_estado (PK), nombre_estado }`
> `Estratos { id_estrato (PK), descripcion }` ← con `CHECK id_estrato BETWEEN 1 AND 6`
> `Usuarios { ..., id_rol (FK), id_estado (FK), id_estrato (FK) }`
> → Cada nombre se almacena una sola vez. Los FK garantizan integridad referencial.

### Violación #4 — Feedback IA mezclado con datos de interacción
En el diseño inicial, el campo `feedback_ia` vivía dentro de `Interacciones_Phishing`. Sin embargo, el feedback es un dato generado por un sistema externo (la IA) y tiene sus propios atributos (fecha de generación, modelo usado). Mezclarlo en la misma tabla genera una dependencia de atributos sobre una entidad que no es la correcta.

> ❌ **Antes (feedback mezclado)**
> `Interacciones_Phishing { id_interaccion, id_evento, fecha_clic, fecha_datos_ingresados, feedback_ia }`
> → `feedback_ia` no depende de la interacción en sí, sino de un proceso externo de IA.
> → No hay trazabilidad: no se sabe cuándo se generó ni qué modelo lo produjo.

> ✓ **Después (entidad propia)**
> `Interacciones_Phishing { id_interaccion, id_evento, fecha_clic, fecha_datos_ingresados }`
> `Feedback_IA { id_feedback (PK), id_interaccion (FK), contenido_feedback, fecha_generacion, modelo_ia }`
> → Cada entidad tiene sus propios atributos y se relacionan por FK.
> → Se puede registrar la fecha y el modelo de IA usado para trazabilidad.

### Resumen de violaciones de 3FN corregidas

| Violación | Tabla afectada | Solución aplicada |
| :--- | :--- | :--- |
| Dependencia transitiva: id_test→puntaje→nivel | Test_Evaluativo | Eliminar id_nivel_asignado. Nivel calculado por app con rangos en Niveles_Dificultad |
| Dato calculado almacenado: puntaje_final | Test_Evaluativo | Eliminar puntaje_final. Se calcula con consulta SUM sobre Respuestas_Test |
| Atributos repetidos: rol, estado, estrato | Usuarios | Crear catálogos Roles, Estados_Usuario, Estratos con FK |
| Feedback IA en tabla de interacciones | Interacciones_Phishing | Crear tabla Feedback_IA con FK a Interacciones_Phishing |

---

## 6. Modelo Final Normalizado (3FN)

Tras aplicar las tres formas normales, el modelo quedó compuesto por 13 tablas bien definidas, sin redundancia, sin dependencias transitivas y con integridad referencial garantizada por claves foráneas.
