### Matriz de trazabilidad: Entidades → Arquetipos → RF → Reglas de negocio

| Entidades (BD) | Arquetipos (Clases JPA) | RF (Requisito Funcional Relacionado) | Reglas de Negocio Aplicadas |
| :--- | :--- | :--- | :--- |
| Usuario | Usuario, Role, EstadoUsuario, Estrato | RF01: Registro.<br>RF02: Autenticación. | RN-01: El registro público está habilitado únicamente para el rol Estudiante.<br>RN-02: Las cuentas con rol Docente o Administrador deben ser creadas exclusivamente por un Administrador. |
| Test_Evaluativo | TestEvaluativo, NivelDificultad | RF04: Evaluación diagnóstica.<br>RF09: Ajuste automático de dificultad. | RN-03: El test teórico tiene carácter diagnóstico; no posee un estado de "reprobado", sino que mide la brecha de conocimiento para adaptar la simulación. |
| Escenario_Phishing | EscenarioPhishing, NivelDificultad | RF04: Ejecución de simulaciones. | RN-04: Todo escenario debe tener un nivel de complejidad definido para emparejarse con el nivel diagnosticado del estudiante. |
| Evento_Simulacion, Feedback_IA | EventoSimulacion, EstadoEvento, InteraccionPhishing, FeedbackIA | RF04: Ejecución de simulaciones.<br>RF05: Retroalimentación inmediata.<br>RF06: Recomendaciones personalizadas. | RN-05: La simulación no puede ejecutarse si el estudiante no ha completado el test diagnóstico previo.<br>RN-06: El sistema debe emitir retroalimentación inmediata tras la interacción antes de cerrar el ciclo. |
| Preguntas_Test, Opciones_Pregunta, Respuestas_Test | PreguntaTest, OpcionPregunta, RespuestaTest | RF04: Evaluación diagnóstica.<br>RF07: Registro de desempeño. | RN-07: Cada pregunta debe tener al menos dos opciones, una de ellas marcada como correcta. |
