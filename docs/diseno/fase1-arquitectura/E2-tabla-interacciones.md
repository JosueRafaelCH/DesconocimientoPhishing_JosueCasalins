### Tabla de interacciones

| Entidad Externa | Tipo (Persona/Sistema/Dispositivo) | Rol / Descripción | Datos que ENVÍA al Sistema | Datos que RECIBE del Sistema | Protocolo / Medio |
| :--- | :--- | :--- | :--- | :--- | :--- |
| Administrador | Persona | Configuración de la campaña y la gestión de la plataforma. | Credenciales , parámetros del sistema | Reportes institucionales, confirmaciones, métricas | Web / HTTPS |
| Docente | Persona | Consulta de datos brutos de simulaciones de escenarios de phishing | Credenciales, solicitud de reportes por grupo | Resultados de estudiantes, estadísticas del curso | Web / HTTPS |
| Estudiante | Persona | Participa en simulaciones phishing | respuestas a simulaciones, evaluaciones de contraseña mediante la IA | Retroalimentación educativa, puntajes, insignias | Web / HTTPS |
| Sistema de Correo Simulado | Sistema | Envía simulaciones de phishing controladas | Eventos de interacción (clic, apertura de enlace) | Confirmación de registro de eventos | API / HTTP |
| Navegador Web del Usuario | Dispositivo | Interfaz de acceso al simulador web | Solicitudes HTTP, formularios de registro, acciones del usuario | Interfaces gráficas, contenido educativo | Web / HTTPS |
| Base de Datos del Sistema | Sistema | Almacena información del sistema | Consultas SQL, almacenamiento de eventos | Datos de usuarios, resultados y estadísticas | Conexión interna segura |
| Sistema de Reportes | Sistema | Genera métricas y estadísticas institucionales | Solicitud de generación de reportes | Reportes estadísticos y gráficas | Web / HTTPS |
