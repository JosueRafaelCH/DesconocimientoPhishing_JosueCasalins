### Matriz de trazabilidad: Clases → Métodos → CU → RF

| Clases (Backend Java) | Métodos Principales | CU (Caso de Uso) | RF (Requisito Funcional) |
| :--- | :--- | :--- | :--- |
| AuthController / UsuarioService | login(), registrarEstudiante(), validarCredenciales() | CU-01: Autenticar Usuario y Registro | RF01: Registro y Autenticación de usuarios. |
| TestService / TestEvaluativo | iniciarTest(), procesarRespuestas(), calcularNivel() | CU-02: Realizar Test Diagnóstico | RF04: Evaluación diagnóstica. ### Matriz de trazabilidad: Clases → Métodos → CU → RF

| Clases (Backend Java) | Métodos Principales | CU (Caso de Uso) | RF (Requisito Funcional) |
| :--- | :--- | :--- | :--- |
| **UsuarioService** | login(), registrar(), validar() | CU-01A, CU-01B | RF01, RF02 |
| **TestService** | iniciarTest(), procesarRespuestas() | CU-02 | RF04, RF07 |
| **SimulacionService** | lanzarCampana(), registrarInteraccion() | CU-04, CU-05 | RF04, RF09 |
| **AnalisisAdaptativoService** | evaluarInteraccion(), generarFeedback() | CU-05 | RF05, RF06 |
| **ReporteService** | generarReporte(), consultarMetricas() | CU-06 | RF10 |
| **EscenarioService** | crear(), listarPorNivel() | CU-03 | RF04 |
