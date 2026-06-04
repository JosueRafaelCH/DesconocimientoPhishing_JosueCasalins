### Matriz de trazabilidad: Tablas → PK → FK → Forma Normal (3FN)

| Tabla | PK | FK | Forma Normal | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| **Roles** | id_rol | — | 3FN | Catálogo de roles de usuario. |
| **Estados_Usuario** | id_estado | — | 3FN | Catálogo de estados de cuenta. |
| **Estratos** | id_estrato | — | 3FN | Catálogo de estratos socioeconómicos. |
| **Niveles_Dificultad** | id_nivel | — | 3FN | Catálogo de niveles de dificultad. |
| **Estados_Evento** | id_estado_evento | — | 3FN | Catálogo de estados de evento de simulación. |
| **Usuarios** | id_usuario | id_rol, id_estado, id_estrato, id_docente_tutor | 3FN | Gestión centralizada de usuarios. |
| **Test_Evaluativo** | id_test | id_usuario, id_nivel | 3FN | Sesiones de diagnóstico. |
| **Preguntas_Test** | id_pregunta | id_nivel | 3FN | Banco de preguntas. |
| **Opciones_Pregunta** | id_opcion | id_pregunta | 3FN | Opciones de selección. |
| **Respuestas_Test** | id_respuesta | id_test, id_pregunta, id_opcion | 3FN | Selección del usuario. |
| **Escenarios_Phishing** | id_escenario | id_nivel | 3FN | Catálogo de escenarios. |
| **Eventos_Simulacion** | id_evento | id_usuario, id_escenario, id_test, id_estado_evento | 3FN | Registro de ejecución. |
| **Interacciones_Phishing** | id_interaccion | id_evento | 3FN | Registro de comportamiento. |
| **Feedback_IA** | id_feedback | id_interaccion | 3FN | Retroalimentación adaptativa. |
