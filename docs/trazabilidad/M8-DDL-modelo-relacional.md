### Matriz de trazabilidad: DDL → Modelo relacional → PK → FK → Restricciones

| Tabla | PK | FK | Restricciones principales |
| :--- | :--- | :--- | :--- |
| **Usuarios** | id_usuario | id_rol, id_estado, id_estrato | UNIQUE(correo), NOT NULL(nombres, apellidos, correo, hash) |
| **Escenarios_Phishing** | id_escenario | id_nivel | NOT NULL(titulo, id_nivel) |
| **Test_Evaluativo** | id_test | id_usuario | DEFAULT(NOW()) |
| **Eventos_Simulacion** | id_evento | id_usuario, id_escenario, id_test, id_estado_evento | NOT NULL(todas las FK) |
| **Interacciones_Phishing** | id_interaccion | id_evento | UNIQUE(id_evento) |
| **Feedback_IA** | id_feedback | id_interaccion | NOT NULL(contenido_feedback) |
