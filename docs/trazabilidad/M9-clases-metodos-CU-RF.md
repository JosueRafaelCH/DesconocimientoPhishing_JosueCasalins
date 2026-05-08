### Matriz de trazabilidad: Clases → Métodos → CU → RF

| Clases (Backend Java) | Métodos Principales | CU (Caso de Uso) | RF (Requisito Funcional) |
| :--- | :--- | :--- | :--- |
| AuthController / UsuarioService | login(), registrarEstudiante(), validarCredenciales() | CU-01: Autenticar Usuario y Registro | RF01: Registro y Autenticación de usuarios. |
| TestService / TestEvaluativo | iniciarTest(), procesarRespuestas(), calcularNivel() | CU-02: Realizar Test Diagnóstico | RF04: Evaluación diagnóstica. RF06: Ajuste de dificultad. |
| SimulacionService / EventoSimulacion | asignarEscenario(), lanzarPhishing(), registrarClic() | CU-03: Ejecutar Simulación Práctica | RF05: Simulación de ataques. RF08: Detección de vulnerabilidades. |
| IAService / EvaluacionIA | analizarComportamiento(), generarFeedbackIA() | CU-04: Análisis y Retroalimentación IA | RF07: Feedback inmediato con IA. |
| ReporteService / ReporteHibrido | generarReporteInmutable(), consultarMetricasGlobales() | CU-05: Consultar Estadísticas y Reportes | RF09: Consolidación de resultados. RF10: Visualización de métricas. |
| EscenarioService / EscenarioPhishing | crearPlantilla(), listarPorNivel(), actualizarEscenario() | CU-06: Gestionar Escenarios y Campañas | RF11: Creación y gestión de plantillas de phishing. |
