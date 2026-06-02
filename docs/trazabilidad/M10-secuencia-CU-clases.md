### Matriz de trazabilidad: Diagramas de secuencia → CU → Clases → Flujos alternativos

| Diagramas de secuencia | CU (Casos de Uso) | Clases involucradas | Flujos alternativos / Excepciones |
| :--- | :--- | :--- | :--- |
| DS-01 Autenticación | CU-01B | AuthController, UsuarioService, UsuarioRepository | Credenciales inválidas, cuenta bloqueada. |
| DS-02 Evaluación Diagnóstica | CU-02 | TestUI, TestService, TestEvaluativo, PreguntaRepository | Abandono de la prueba o Timeout. |
| DS-03 Simulación y Feedback | CU-04, CU-05 | SimulacionUI, SimulacionService, AnalisisAdaptativoService | Clic en phishing, correo ignorado, latencia alta. |
| DS-04 Consulta de Estadísticas | CU-06 | DashboardDocente, ReporteService | Filtros vacíos o sin datos. |
| DS-05 Gestión de Escenarios | CU-03 | AdminUI, EscenarioService, EscenarioPhishing | Campos incompletos, formato URL inválido. |
