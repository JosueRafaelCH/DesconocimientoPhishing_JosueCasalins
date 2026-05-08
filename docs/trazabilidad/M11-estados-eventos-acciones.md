### Matriz de trazabilidad: Estados → Eventos → Acciones → RF/Reglas de negocio

| Estado Inicial | Evento (Disparador) | Acción del Sistema | RF / Regla de negocio |
| :--- | :--- | :--- | :--- |
| Inicial (Login) | Autenticación válida | Redirigir al Dashboard según el rol del usuario. | RF01 / RN-01: Control de acceso por roles. |
| Dashboard Estudiante | Clic en Iniciar Ciclo | Cargar banco de preguntas y mostrar interfaz del test. | RF04 / RN-03: Inicio de evaluación diagnóstica. |
| Test en Progreso | Envío de respuestas | Calcular puntaje y determinar el nivel de conocimiento. | RF06 / RN-03: El test mide brecha, no bloquea. |
| Nivel Diagnosticado | Transición automática | Seleccionar y cargar plantilla de phishing según el nivel. | RF05 / RN-04: Dependencia entre test y simulación. |
| Simulación Activa | Clic en enlace malicioso | Registrar vulnerabilidad y activar motor de análisis IA. | RF08 / RN-06: Detección de interacción de riesgo. |
| Simulación Activa | Ignorar correo (Cierre) | Registrar comportamiento seguro y activar motor IA. | RF07 / RN-06: Evaluación de conducta preventiva. |
| Feedback Generado | Lectura de resultados | Mostrar retroalimentación educativa y métricas de IA. | RF07 / RN-07: Feedback inmediato y educativo. |
| Ciclo Completado | Finalización de sesión | Generar reporte y marcar datos como no modificables. | RF09 / RN-08: Inmutabilidad del reporte final. |
