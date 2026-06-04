### Matriz de trazabilidad: Relaciones entre Entidades y Cardinalidades

| Entidad A | Relación | Entidad B | Cardinalidad | Regla de negocio / Descripción |
| :--- | :--- | :--- | :--- | :--- |
| **Roles** | tiene | **Usuarios** | 1:N | Un rol puede ser asignado a muchos usuarios. |
| **Estados_Usuario** | tiene | **Usuarios** | 1:N | Un estado se aplica a muchos usuarios. |
| **Estratos** | tiene | **Usuarios** | 1:N | Un estrato se aplica a muchos usuarios. |
| **Usuarios** | realiza | **Test_Evaluativo** | 1:N | Un usuario realiza uno o más tests. |
| **Usuarios** | recibe | **Eventos_Simulacion** | 1:N | Un usuario puede participar en múltiples simulaciones. |
| **Usuarios** (docente) | tutor de | **Usuarios** (estudiante) | 1:N | Un docente puede tutorar a muchos estudiantes. |
| **Test_Evaluativo** | contiene | **Respuestas_Test** | 1:N | Un test tiene múltiples respuestas. |
| **Test_Evaluativo** | genera | **Eventos_Simulacion** | 1:N | Un test puede dar origen a varios eventos de simulación. |
| **Niveles_Dificultad** | clasifica | **Preguntas_Test** | 1:N | Un nivel tiene muchas preguntas. |
| **Niveles_Dificultad** | clasifica | **Escenarios_Phishing** | 1:N | Un nivel tiene muchos escenarios. |
| **Niveles_Dificultad** | clasifica | **Test_Evaluativo** | 1:N | Un nivel puede estar asociado a varios tests. |
| **Preguntas_Test** | tiene | **Opciones_Pregunta** | 1:N | Una pregunta tiene varias opciones de respuesta. |
| **Preguntas_Test** | contestada en | **Respuestas_Test** | 1:N | Una pregunta puede ser respondida en múltiples tests. |
| **Escenarios_Phishing** | se asigna a | **Eventos_Simulacion** | 1:N | Un escenario puede ser enviado en muchos eventos. |
| **Estados_Evento** | clasifica | **Eventos_Simulacion** | 1:N | Un estado puede tener muchos eventos. |
| **Eventos_Simulacion** | genera | **Interacciones_Phishing** | 1:1 | Una interacción corresponde exactamente a un evento. |
| **Interacciones_Phishing** | produce | **Feedback_IA** | 1:N | Una interacción puede generar múltiples consejos educativos (uno por técnica de phishing detectada). |
