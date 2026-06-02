### Matriz de trazabilidad: Tablas → PK → FK → Forma Normal (3FN)

| Tabla | PK | FK | Forma Normal | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| **Usuarios** | id_usuario | id_rol, id_estado, id_estrato | 3FN | Gestión centralizada de usuarios. |
| **Test_Evaluativo** | id_test | id_usuario | 3FN | Sesiones de diagnóstico. |
| **Escenarios_Phishing** | id_escenario | id_nivel | 3FN | Catálogo de escenarios. |
| **Eventos_Simulacion** | id_evento | id_usuario, id_escenario, id_test, id_estado_evento | 3FN | Registro de ejecución. |
| **Interacciones_Phishing** | id_interaccion | id_evento | 3FN | Registro de comportamiento. |
| **Feedback_IA** | id_feedback | id_interaccion | 3FN | Retroalimentación adaptativa. |
| **Preguntas_Test** | id_pregunta | id_nivel | 3FN | Banco de preguntas. |
| **Opciones_Pregunta** | id_opcion | id_pregunta | 3FN | Opciones de selección. |
| **Respuestas_Test** | id_respuesta | id_test, id_pregunta, id_opcion | 3FN | Selección del usuario. |
