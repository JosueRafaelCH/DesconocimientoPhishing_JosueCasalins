### Matriz de trazabilidad: Diagramas de secuencia → CU → Clases → Flujos alternativos

| Diagramas de secuencia | CU (Casos de Uso) | Clases involucradas | Flujos alternativos / Excepciones |
| :--- | :--- | :--- | :--- |
| DS-01 Autenticación | CU-01 Ingreso al sistema | LoginUI, AuthController, UsuarioService, UsuarioRepository | Credenciales inválidas, usuario bloqueado o error de conexión con la base de datos. |
| DS-02 Evaluación Diagnóstica | CU-02 Realizar Test Diagnóstico | TestUI, TestService, TestEvaluativo, PreguntaRepository | Abandono de la prueba por parte del estudiante o agotamiento del tiempo (Timeout). |
| DS-03 Simulación y Feedback IA | CU-03 Ejecutar Simulación Práctica | SimulacionUI, SimulacionService, IAService, EventoSimulacion | El estudiante hace clic en el enlace malicioso (Cae en el phishing) o el correo es ignorado. |
| DS-04 Consulta de Estadísticas | CU-05 Consultar Reportes y Métricas | DashboardDocente, ReporteService, ReporteHibrido, JasperReports | Filtro de búsqueda sin resultados o ausencia de datos en el rango de fechas seleccionado. |
| DS-05 Gestión de Campañas | CU-06 Crear Escenario de Phishing | AdminUI, EscenarioService, EscenarioPhishing, UsuarioRepository | Error en el guardado por campos obligatorios incompletos o nombre de plantilla duplicado. |
