### Matriz de trazabilidad: Clases → Métodos → CU → RF

| Clases (Backend Java) | Métodos Principales | CU (Caso de Uso) | RF (Requisito Funcional) |
| :--- | :--- | :--- | :--- |
| **AuthController** / **SimiladorService** | `registrar()`, `loginPage()`, `loginError()` | CU-01A: Registrar Usuario<br>CU-01B: Autenticar Usuario | RF01: Registro con correo institucional.<br>RF02: Autenticación segura. |
| **TestController** / **SimiladorService** | `seleccionarNivel()`, `iniciarTest()`, `preguntasTest()`, `enviarTest()`, `resultados()` | CU-02: Realizar Test Diagnóstico | RF04: Ejecución de simulaciones.<br>RF07: Registro de desempeño. |
| **SimulacionController** / **SimiladorService** | `listarSimulaciones()`, `verSimulacion()` | CU-05: Procesar Interacción de Simulación | RF04: Ejecución de simulaciones.<br>RF05: Retroalimentación inmediata.<br>RF07: Registro de desempeño. |
| **AdminController** / **SimiladorService** | `panel()`, CRUD `listar/crear/editar/eliminar` para usuarios, escenarios y preguntas, `asignar()` | CU-01B: Autenticar Usuario<br>CU-03: Gestionar Escenarios<br>CU-04: Desplegar Simulación | RF02: Autenticación.<br>RF04: Ejecución de simulaciones.<br>RF10: Campañas institucionales. |
| **DocenteController** / **SimiladorService** | `panel()`, `listEstudiantes()`, `verEstudiante()` | CU-06: Consultar Métricas de Rendimiento | RF10: Campañas institucionales. |
| **DashboardController** / **SimiladorService** | `panel()` | CU-01B: Autenticar Usuario<br>CU-02: Realizar Test Diagnóstico | RF02: Autenticación.<br>RF04: Ejecución de simulaciones. |
