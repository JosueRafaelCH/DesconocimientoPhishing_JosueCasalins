Matriz de trazabilidad: Tablas relacionales → MER → PK → FK → Forma normal

| Relación | Entidades involucradas | Cardinalidad | Regla de negocio que la justifica |
| :--- | :--- | :--- | :--- |
| realiza | Usuario — Test_Evaluativo | 1:N | RN-01: Un estudiante puede realizar múltiples ciclos de evaluación (tests) a lo largo del tiempo. |
| crea | Usuario — Escenario_Phishing | 1:N | RN-02: Un administrador puede crear múltiples plantillas de escenarios de phishing. |
| contiene | Test_Evaluativo — Detalle_Test_Pregunta | 1:N | RN-03: Un test diagnóstico se compone de múltiples preguntas para evaluar al estudiante. |
| dispara | Test_Evaluativo — Evento_Simulacion | 1:1 | RN-04: Un test diagnóstico debe disparar exactamente un evento de simulación práctica (dependencia estricta). |
| utiliza | Escenario_Phishing — Evento_Simulacion | 1:N | RN-05: Un mismo escenario de phishing puede ser utilizado en varios eventos de simulación de diferentes estudiantes. |
| procesa | Evento_Simulacion — Evaluacion_IA | 1:1 | RN-06: Cada evento de simulación debe ser procesado una única vez por la IA para generar retroalimentación. |
| consolida | Evaluacion_IA — Reporte_Hibrido | 1:1 | RN-07: El análisis final de la IA se consolida en un único reporte híbrido por cada ciclo completado. |
