### Matriz de trazabilidad: Estados → Eventos → Acciones → RF/Reglas de negocio

| Estado Inicial | Evento (Disparador) | Acción del Sistema | RF / Regla de negocio |
| :--- | :--- | :--- | :--- |
| Inicial (Login) | Autenticación válida | Redirigir al Dashboard según el rol del usuario. | RF02 / RN-01: Control de acceso por roles. |
| Dashboard Estudiante | Clic en "Iniciar Nuevo Ciclo" | Mostrar selección de nivel de dificultad. | RF04 / RN-03: Inicio de evaluación diagnóstica. |
| Selección de Nivel | Clic en "Iniciar Test" | Cargar banco de preguntas del nivel seleccionado y mostrar interfaz del test. | RF04 / RN-03: El test mide brecha, no bloquea. |
| Test en Progreso | Envío de respuestas | Calcular puntaje, determinar nivel y redirigir a resultados. | RF07 / RN-03: Registro de desempeño. |
| Resultados del Test | Transición automática al dashboard | Mostrar calificación obtenida y permitir explorar simulaciones. | RF04, RF07 |
| Simulación Asignada | Clic en ver detalle de evento | Mostrar información de la simulación asignada. | RF04 / RN-05: Dependencia entre test y simulación. |
| Ciclo Completado | Finalización de sesión | Dashboard actualizado con test y eventos registrados. | RF07, RF09 |
