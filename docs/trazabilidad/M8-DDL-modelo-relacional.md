### Matriz de trazabilidad: DDL → Modelo relacional → PK → FK → Restricciones

| Tabla | PK | FK | Restricciones principales |
| :--- | :--- | :--- | :--- |
| **Roles** | id_rol | — | NOT NULL(nombre_rol), UNIQUE(nombre_rol) |
| **Estados_Usuario** | id_estado | — | NOT NULL(nombre_estado), UNIQUE(nombre_estado) |
| **Estratos** | id_estrato | — | NOT NULL(descripcion) |
| **Niveles_Dificultad** | id_nivel | — | NOT NULL(nombre_nivel, puntaje_minimo, puntaje_maximo) |
| **Estados_Evento** | id_estado_evento | — | NOT NULL(nombre_estado), UNIQUE(nombre_estado) |
| **Usuarios** | id_usuario | id_rol, id_estado, id_estrato, id_docente_tutor | UNIQUE(correo_institucional), NOT NULL(nombres, apellidos, correo_institucional, contrasena_hash) |
| **Test_Evaluativo** | id_test | id_usuario, id_nivel | DEFAULT(NOW() para fecha_realizacion) |
| **Preguntas_Test** | id_pregunta | id_nivel | NOT NULL(enunciado) |
| **Opciones_Pregunta** | id_opcion | id_pregunta | NOT NULL(texto_opcion, es_correcta) |
| **Respuestas_Test** | id_respuesta | id_test, id_pregunta, id_opcion | FK compuesta |
| **Escenarios_Phishing** | id_escenario | id_nivel | NOT NULL(titulo) |
| **Eventos_Simulacion** | id_evento | id_usuario, id_escenario, id_test, id_estado_evento | NOT NULL(todas las FK) |
| **Interacciones_Phishing** | id_interaccion | id_evento | UNIQUE(id_evento) |
| **Feedback_IA** | id_feedback | id_interaccion | NOT NULL(contenido_feedback) |
