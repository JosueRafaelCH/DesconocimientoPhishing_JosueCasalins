### Matriz de trazabilidad: Componentes → Arquetipos → RF → Interfaces

| Componente / Módulo | Arquetipo(s) base | RF Asociado | Interfaz de Usuario (Vista) |
| :--- | :--- | :--- | :--- |
| **Módulo de Usuarios** | Usuario | RF01, RF02, RF03 | UI_Login, UI_Registro, UI_Recuperar_Contrasena, UI_Restablecer_Contrasena |
| **Módulo Evaluativo** | Test Diagnóstico | RF04, RF07 | UI_Dashboard_Estudiante, UI_Test_Niveles, UI_Test_Preguntas, UI_Test_Historial, UI_Test_Resultados |
| **Módulo de Simulación** | Interacción de Simulación | RF04, RF05, RF09 | UI_Bandeja_Simulacion, UI_Simulacion_Interactiva |
| **Motor de Análisis** | Interacción de Simulación, FeedbackIA | RF05, RF06, RF09 | UI_Recomendaciones, UI_Simulacion_Interactiva |
| **Módulo de Analítica** | Métricas de Rendimiento | RF10 | UI_Dashboard_Docente, UI_Docente_Estudiantes, UI_Detalle_Estudiante |
| **Módulo de Gestión** | Escenario de Phishing | RF04, RF10 | UI_Admin_Escenarios, UI_Admin_Preguntas, UI_Admin_Asignar, UI_Admin_Campanas |
| **Módulo de Seguridad** | Usuario | RF08 | UI_Evaluar_Contrasena |
