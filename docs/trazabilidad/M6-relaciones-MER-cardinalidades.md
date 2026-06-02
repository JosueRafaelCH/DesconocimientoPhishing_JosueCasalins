### Matriz de trazabilidad: Relaciones entre Entidades y Cardinalidades

| Entidad A | Relación | Entidad B | Cardinalidad | Regla de negocio / Descripción |
| :--- | :--- | :--- | :--- | :--- |
| **Roles** | tiene | **Usuarios** | 1:N | Un rol puede ser asignado a muchos usuarios. |
| **Estratos** | tiene | **Usuarios** | 1:N | Un estrato se aplica a muchos usuarios. |
| **Usuarios** | realiza | **Test_Evaluativo** | 1:N | Un usuario realiza uno o más tests. |
| **Test_Evaluativo** | contiene | **Respuestas_Test** | 1:N | Un test tiene múltiples respuestas. |
| **Preguntas_Test** | tiene | **Opciones_Pregunta** | 1:N | Una pregunta tiene varias opciones de respuesta. |
| **Escenarios_Phishing** | se asigna a | **Eventos_Simulacion** | 1:N | Un escenario puede ser enviado en muchos eventos. |
| **Usuarios** | recibe | **Eventos_Simulacion** | 1:N | Un usuario puede participar en múltiples simulaciones. |
| **Eventos_Simulacion** | genera | **Interacciones_Phishing** | 1:1 | Una interacción corresponde exactamente a un evento. |
| **Interacciones_Phishing** | produce | **Feedback_IA** | 1:1 | Una interacción genera un consejo educativo específico. |
