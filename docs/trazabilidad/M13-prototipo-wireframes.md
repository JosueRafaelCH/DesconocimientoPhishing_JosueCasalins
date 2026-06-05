### Matriz de trazabilidad: Prototipo → Wireframes → Interacciones → Pantallas destino

| Prototipo (Módulo) | Wireframe | Interacción del Usuario | Pantalla Destino |
| :--- | :--- | :--- | :--- |
| **Autenticación** | | | |
| Módulo de Acceso | ![Login](../imagenes/screenshots/auth-login.png) | Ingresar credenciales y hacer clic en "Iniciar sesión" | UI_Dashboard (según rol) |
| Módulo de Acceso | ![Login](../imagenes/screenshots/auth-login.png) | Clic en "¿No tienes cuenta? Regístrate" | UI_Registro |
| Módulo de Acceso | ![Login](../imagenes/screenshots/auth-login.png) | Clic en "Olvidé mi contraseña" | UI_Recuperar_Contrasena |
| Módulo de Registro | ![Registro](../imagenes/screenshots/auth-register.png) | Clic en "Crear cuenta" | UI_Login (Confirmación) |
| Módulo de Acceso | ![Recuperar](../imagenes/screenshots/auth-recuperar-contrasena.png) | Ingresar correo y clic en "Enviar enlace" | UI_Login (Correo enviado) |
| Módulo de Acceso | ![Restablecer](../imagenes/screenshots/auth-restablecer-contrasena.png) | Ingresar nueva contraseña y clic en "Restablecer" | UI_Login |
| **Admin** | | | |
| Admin Panel | ![Panel](../imagenes/screenshots/admin-panel.png) | Navegación por tarjetas de acceso rápido | UI_Admin_Usuarios / UI_Admin_Preguntas / UI_Admin_Escenarios / UI_Admin_Asignar |
| Gestión Usuarios | ![Usuarios](../imagenes/screenshots/admin-usuarios.png) | Clic en "+ Nuevo Usuario" | UI_Admin_Usuario_Form |
| Gestión Usuarios | ![Usuarios](../imagenes/screenshots/admin-usuarios.png) | Clic en "Editar" en fila de usuario | UI_Admin_Usuario_Form |
| Gestión Usuarios | ![User form](../imagenes/screenshots/admin-usuario-form.png) | Clic en "Guardar" | UI_Admin_Usuarios |
| Gestión Preguntas | ![Preguntas](../imagenes/screenshots/admin-preguntas.png) | Clic en "+ Nueva Pregunta" | UI_Admin_Pregunta_Form |
| Gestión Preguntas | ![Pregunta form](../imagenes/screenshots/admin-pregunta-form.png) | Clic en "Guardar" | UI_Admin_Preguntas |
| Gestión Escenarios | ![Escenarios](../imagenes/screenshots/admin-escenarios.png) | Clic en "+ Nuevo Escenario" | UI_Admin_Escenario_Form |
| Gestión Escenarios | ![Escenario form](../imagenes/screenshots/admin-escenario-form.png) | Clic en "Guardar" | UI_Admin_Escenarios |
| Asignar Simulación | ![Asignar](../imagenes/screenshots/admin-asignar.png) | Seleccionar usuario + escenario y clic en "Asignar" | UI_Admin_Asignar |
| Campañas Admin | ![Campañas](../imagenes/screenshots/admin-campanas.png) | Clic en "+ Nueva Campaña" | UI_Admin_Campana_Form |
| Campaña Admin | ![Camp. form](../imagenes/screenshots/admin-campana-form.png) | Clic en "Guardar" | UI_Admin_Campanas |
| **Docente** | | | |
| Docente Panel | ![Panel doc.](../imagenes/screenshots/docente-panel.png) | Clic en "Ver detalle" de un estudiante | UI_Detalle_Estudiante |
| Docente Estudiantes | ![Estudiantes](../imagenes/screenshots/docente-estudiantes.png) | Clic en "Ver" de un estudiante | UI_Detalle_Estudiante |
| Docente Detalle | ![Detalle](../imagenes/screenshots/docente-estudiante-detalle.png) | Revisar historial de tests del estudiante | UI_Detalle_Estudiante |
| **Estudiante** | | | |
| Estudiante Dashboard | ![Dashboard](../imagenes/screenshots/estudiante-dashboard.png) | Clic en tarjeta de test | UI_Test_Niveles |
| Estudiante Dashboard | ![Dashboard](../imagenes/screenshots/estudiante-dashboard.png) | Clic en "Simulaciones" en menú | UI_Bandeja_Simulacion |
| Selección Nivel | ![Niveles](../imagenes/screenshots/estudiante-test.png) | Clic en "Iniciar Test" de un nivel | UI_Test_Preguntas |
| Responder Test | ![Preguntas](../imagenes/screenshots/estudiante-preguntas-test.png) | Seleccionar respuesta y clic en "Siguiente" | UI_Test_Preguntas (siguiente pregunta) |
| Responder Test | ![Preguntas](../imagenes/screenshots/estudiante-preguntas-test.png) | Clic en "Finalizar" en última pregunta | UI_Test_Resultados |
| Historial Tests | ![Historial](../imagenes/screenshots/estudiante-test-historial.png) | Clic en "Ver resultados" de un test | UI_Test_Resultados |
| Resultados | ![Resultados](../imagenes/screenshots/estudiante-test-resultados.png) | Revisar respuestas correctas/incorrectas | UI_Test_Historial |
| Simulaciones | ![Simulaciones](../imagenes/screenshots/estudiante-simulaciones.png) | Clic en "Interactuar" de simulación pendiente | UI_Simulacion_Interactiva |
| Simulación Interactiva | ![Interactiva](../imagenes/screenshots/estudiante-simulacion-interactiva.png) | Clic en "Es phishing / Es legítimo / No estoy seguro" | UI_Simulacion_Interactiva (feedback) |
| Recomendaciones | ![Recomend.](../imagenes/screenshots/estudiante-recomendaciones.png) | Leer recomendaciones personalizadas | UI_Recomendaciones |
| Evaluar Contraseña | ![Eval. pass](../imagenes/screenshots/estudiante-evaluar-contrasena.png) | Ingresar contraseña para evaluar fortaleza | UI_Evaluar_Contrasena |
| Campañas Estudiante | ![Camp. est.](../imagenes/screenshots/estudiante-campanas.png) | Clic en "Iniciar" de campaña | UI_Simulacion_Interactiva |
| **Error** | | | |
| Error 403 | ![403](../imagenes/screenshots/error-403.png) | Redirección automática o clic en volver | Login / Dashboard |
| Error 404 | ![404](../imagenes/screenshots/error-404.png) | Redirección automática o clic en volver | Login / Dashboard |
| Error 500 | ![500](../imagenes/screenshots/error-500.png) | Redirección automática o clic en volver | Login / Dashboard |

**Total: 30 wireframes** con sus interacciones y pantallas destino.
