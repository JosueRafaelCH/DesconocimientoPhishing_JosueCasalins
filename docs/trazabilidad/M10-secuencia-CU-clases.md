### Matriz de trazabilidad: Diagramas de secuencia → CU → Clases → Flujos alternativos

| Diagramas de secuencia | CU (Casos de Uso) | Clases involucradas | Flujos alternativos / Excepciones |
| :--- | :--- | :--- | :--- |
| DS-01 Autenticación | CU-01B | AuthController, SimiladorService, Usuario (JPA Repository) | Credenciales inválidas, cuenta bloqueada. |
| DS-02 Evaluación Diagnóstica | CU-02 | TestController, SimiladorService, TestEvaluativo, PreguntaTest, NivelDificultad, RespuestaTest | Abandono de la prueba o Timeout. |
| DS-03 Simulación y Feedback | CU-05 | SimulacionController, SimiladorService, EventoSimulacion, FeedbackIA | Clic en phishing, correo ignorado. |
| DS-04 Consulta de Estadísticas | CU-06 | DocenteController, SimiladorService, TestEvaluativo, RespuestaTest | Filtros vacíos o sin datos. |
| DS-05 Gestión de Escenarios | CU-03 | AdminController, SimiladorService, EscenarioPhishing, NivelDificultad | Campos incompletos, formato URL inválido. |
