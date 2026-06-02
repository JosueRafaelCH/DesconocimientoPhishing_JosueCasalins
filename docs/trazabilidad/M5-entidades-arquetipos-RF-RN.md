### Matriz de trazabilidad: Entidades → Arquetipos → RF → Reglas de negocio

| Entidades (BD) | Arquetipos (Clases POO) | RF (Requisito Funcional Relacionado) | Reglas de Negocio Aplicadas |
| :--- | :--- | :--- | :--- |
| Usuario | Usuario, Estudiante, Docente, Administrador | RF01: Registro.<br><br>RF02: Autenticación. | 1. El registro público está habilitado únicamente para el rol Estudiante.<br><br>2. Las cuentas con rol Docente deben ser creadas exclusivamente por un Administrador. |
| Test_Evaluativo | TestEvaluativo | RF04: Evaluación diagnóstica.<br><br>RF09: Ajuste automático de dificultad. | 3. El test teórico tiene carácter diagnóstico; no posee un estado de "reprobado", sino que mide la brecha de conocimiento para adaptar la simulación. |
| Escenario_Phishing | EscenarioPhishing | RF04: Ejecución de simulaciones. | 4. Todo escenario debe tener un nivel de complejidad definido para emparejarse con el nivel diagnosticado del estudiante. |
| Evento_Simulacion, Feedback | EventoSimulacion, FeedbackAdaptativo | RF04: Ejecución de simulaciones.<br><br>RF05: Retroalimentación inmediata.<br><br>RF06: Recomendaciones personalizadas. | 5. La simulación no puede ejecutarse si el estudiante no ha completado el test diagnóstico previo.<br><br>6. El sistema debe emitir retroalimentación inmediata tras la interacción antes de cerrar el ciclo. |
| Reporte | Reporte | RF10: Campañas institucionales. | 7. Una vez consolidado el ciclo de aprendizaje, el reporte pasa a ser inmutable (solo lectura).<br><br>8. Los datos exportados para los tableros del docente deben estar obligatoriamente anonimizados. |
