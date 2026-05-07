## Requisitos Funcionales

**RF01. Registro de usuario con correo institucional**
El sistema debe permitir el registro de nuevos usuarios validando que el correo utilizado pertenezca al dominio institucional (@itm.edu.co).

**RF02. Autenticación segura**
El usuario debe poder iniciar sesión en el simulador utilizando su correo institucional y una contraseña que cumpla las políticas de seguridad (mínimo 8 caracteres, una mayúscula, un número y un símbolo).

**RF03. Recuperación de contraseña**
El sistema debe ofrecer la opción de recuperar el acceso mediante el envío de un enlace de restablecimiento al correo institucional, con una validez máxima de 30 minutos.

**RF04. Ejecución de simulaciones de phishing**
El sistema debe ofrecer ejercicios que simulen correos de phishing utilizando datos completamente ficticios y sin requerir información real del usuario.

**RF05. Retroalimentación inmediata**
Después de cada simulación, el sistema debe mostrar indicadores visuales de acierto o error, junto con una breve explicación educativa de cada decisión tomada.

**RF06. Recomendaciones personalizadas**
El sistema debe generar sugerencias específicas para que el usuario mejore su capacidad de detección de phishing, basadas en sus errores y aciertos anteriores.

**RF07. Registro de desempeño**
Cada simulación debe quedar registrada con su fecha, tipo de ejercicio, nivel de dificultad y puntaje obtenido, sin incluir datos personales sensibles.

**RF08. Evaluación de contraseñas**
El usuario podrá ingresar ejemplos de contraseñas y recibir una puntuación de fortaleza junto con recomendaciones para mejorar su seguridad y evitar vulnerabilidades ante ataques de phishing.

**RF09. Ajuste automático de dificultad**
El sistema debe adaptar la complejidad de las simulaciones de acuerdo con el desempeño histórico del usuario, aumentando o reduciendo el nivel según corresponda.

**RF10. Campañas institucionales**
El administrador debe tener la opción de lanzar campañas masivas de simulaciones (por ejemplo, de phishing institucional), configurando fechas y visualizando métricas globales de desempeño.

## Requisitos No Funcionales

**RNF01. Seguridad de contraseñas**
Las contraseñas deben almacenarse cifradas utilizando un algoritmo seguro como bcrypt o equivalente.

**RNF02. Protección de datos**
Las simulaciones y ejercicios no deben requerir ni almacenar información real de los usuarios.

**RNF03. Usabilidad**
La interfaz del sistema debe ser intuitiva, visualmente clara y ofrecer mensajes de retroalimentación inmediatos y comprensibles.

**RNF04. Rendimiento**
El tiempo de carga de las simulaciones no debe superar los tres segundos bajo condiciones normales de conexión.

**RNF05. Escalabilidad**
El sistema debe soportar la conexión simultánea de al menos 500 usuarios sin pérdida significativa de rendimiento.

**RNF06. Disponibilidad**
El simulador debe garantizar una disponibilidad mínima del 99% mensual.

**RNF07. Compatibilidad**
El sistema debe ser accesible desde los principales navegadores web modernos como Chrome, Edge y Firefox.

**RNF08. Accesibilidad**
Los componentes visuales deben cumplir con los estándares de accesibilidad WCAG 2.1 nivel AA.

**RNF09. Mantenibilidad**
El código fuente debe seguir una arquitectura modular (por ejemplo, MVC o microservicios) que facilite su mantenimiento y ampliación.

**RNF10. Privacidad**
Los reportes deben anonimizar toda la información personal antes de ser exportados o compartidos.

**RNF11. Portabilidad**
La aplicación debe poder desplegarse en entornos en la nube como AWS, Azure o Google Cloud.

**RNF12. Auditabilidad**
El sistema debe mantener un registro de todas las acciones del usuario para fines de control y análisis.

## Casos de Uso del Simulador

### CU-01: Autenticación y Gestión de Cuenta (Login y Registro)
* **Actor principal:** Estudiante / Docente / Administrador
* **Actores secundarios:** Sistema de Base de Datos
* **Descripción:** Permite que cualquier usuario se registre o inicie sesión en el sistema y sea redirigido según su rol.
* **Precondiciones:** El sistema debe estar disponible.
* **Postcondiciones:** El usuario queda autenticado y redirigido al panel correspondiente.
* **RF asociado:** RF-01

**Flujo principal**
1. El usuario accede a la plataforma web del simulador.
2. El sistema muestra la pantalla principal con la opción de Iniciar Sesión.
3. El usuario selecciona la opción “Registrarse” (si no tiene cuenta).
4. El sistema muestra el formulario de registro.
5. El usuario ingresa nombre completo, correo institucional y contraseña.
6. El sistema valida que el correo no esté registrado previamente.
7. El sistema valida que la contraseña cumpla las políticas de seguridad.
8. El usuario confirma el registro.
9. El sistema almacena el usuario en la base de datos.
10. El sistema asigna automáticamente el rol correspondiente (por defecto Estudiante).
11. El sistema muestra confirmación de registro exitoso.
12. El sistema redirige al usuario a la pantalla de inicio de sesión.
13. El usuario ingresa correo y contraseña.
14. El sistema valida credenciales contra la base de datos.
15. El sistema identifica el rol asociado a la cuenta.
16. El sistema crea la sesión activa.
17. El sistema redirige al usuario al panel correspondiente (Estudiante, Docente o Administrador).
18. El sistema muestra el dashboard según el rol autenticado.

**Flujos alternos**
* **A1: Credenciales inválidas**
    * 14a. El sistema detecta contraseña incorrecta.
    * 14b. El sistema muestra mensaje “correo o contraseña incorrectos”.
    * 14c. El usuario reintenta el inicio de sesión.
* **A2: Correo ya registrado**
    * 6a. El sistema detecta que el correo ya existe.
    * 6b. El sistema muestra mensaje “correo ya registrado”.
    * 6c. El usuario selecciona iniciar sesión.
* **A3: Contraseña no cumple requisitos**
    * 7a. El sistema detecta que la contraseña es insegura.
    * 7b. El sistema muestra mensaje con requisitos mínimos.
    * 7c. El usuario ingresa una contraseña válida.
* **A4: Usuario sin rol asignado**
    * 15a. El sistema no encuentra rol asociado.
    * 15b. El sistema bloquea acceso y muestra error.
    * 15c. El administrador debe revisar la cuenta.

---

### CU-02: Realizar Test Evaluativo y Asignación de Nivel
* **Actor principal:** Estudiante
* **Actores secundarios:** Sistema
* **Descripción:** Permite que el estudiante realice un test diagnóstico inicial y reciba un nivel de vulnerabilidad asignado automáticamente.
* **Precondiciones:** El estudiante debe estar autenticado.
* **Postcondiciones:** El estudiante obtiene un nivel asignado (Fácil, Medio, Difícil).
* **RF asociado:** RF-02, RF-03

**Flujo principal**
1. El estudiante inicia sesión en el sistema.
2. El sistema muestra el dashboard del estudiante.
3. El estudiante selecciona la opción “Realizar Test Diagnóstico”.
4. El sistema muestra una explicación del objetivo del test.
5. El estudiante confirma el inicio del test.
6. El sistema carga el banco de preguntas disponible.
7. El sistema presenta la primera pregunta del test.
8. El estudiante selecciona una respuesta.
9. El sistema registra la respuesta del estudiante.
10. El sistema carga la siguiente pregunta.
11. El estudiante responde todas las preguntas del test.
12. El estudiante selecciona la opción “Finalizar Test”.
13. El sistema valida que todas las preguntas estén respondidas.
14. El sistema calcula el puntaje obtenido.
15. El sistema procesa el puntaje para determinar el nivel de vulnerabilidad.
16. El sistema asigna automáticamente el nivel (Fácil, Medio o Difícil).
17. El sistema actualiza el perfil del estudiante con el nivel asignado.
18. El sistema muestra el resultado final y el nivel obtenido.
19. El estudiante confirma la finalización.
20. El sistema redirige al estudiante al dashboard con el nivel actualizado.

**Flujos alternos**
* **A1: Preguntas sin responder**
    * 13a. El sistema detecta preguntas sin respuesta.
    * 13b. El sistema muestra alerta indicando cuáles faltan.
    * 13c. El estudiante completa las respuestas y finaliza nuevamente.
* **A2: Abandono del test**
    * 12a. El estudiante cierra el test antes de finalizar.
    * 12b. El sistema guarda el progreso parcial.
    * 12c. El estudiante podrá retomar el test posteriormente.
* **A3: Error al cargar preguntas**
    * 6a. El sistema no puede cargar el banco de preguntas.
    * 6b. El sistema muestra mensaje de error.
    * 6c. El sistema redirige al dashboard.

---

### CU-03: Gestionar Escenarios y Plantillas de Phishing
* **Actor principal:** Administrador / Docente
* **Actores secundarios:** Sistema de Base de Datos
* **Descripción:** Permite crear, editar y administrar plantillas de phishing que serán usadas en simulaciones.
* **Precondiciones:** El usuario debe estar autenticado con rol Docente o Administrador.
* **Postcondiciones:** La plantilla queda almacenada y disponible para futuras campañas.
* **RF asociado:** RF-04

**Flujo principal**
1. El docente o administrador inicia sesión.
2. El sistema muestra el panel correspondiente.
3. El actor selecciona la opción “Gestionar Escenarios de Phishing”.
4. El sistema muestra la lista de escenarios existentes.
5. El actor selecciona la opción “Crear nuevo escenario”.
6. El sistema muestra el formulario de creación de escenario.
7. El actor ingresa el remitente falso, asunto y cuerpo del mensaje.
8. El actor ingresa una URL simulada o enlace falso.
9. El actor selecciona el nivel de dificultad del escenario.
10. El actor selecciona “Guardar”.
11. El sistema valida que los campos obligatorios estén completos.
12. El sistema registra la plantilla en la base de datos.
13. El sistema confirma que el escenario fue creado correctamente.
14. El sistema muestra el nuevo escenario en la lista general.
15. El actor selecciona un escenario existente para editarlo.
16. El sistema muestra el formulario con datos cargados.
17. El actor modifica contenido o dificultad.
18. El actor guarda los cambios.
19. El sistema actualiza el escenario en la base de datos.
20. El sistema muestra confirmación de edición exitosa.

**Flujos alternos**
* **A1: Campos incompletos**
    * 11a. El sistema detecta campos vacíos.
    * 11b. El sistema bloquea el guardado.
    * 11c. El actor completa la información.
* **A2: Eliminación de escenario**
    * 15a. El actor selecciona “Eliminar”.
    * 15b. El sistema solicita confirmación.
    * 15c. El actor confirma eliminación.
    * 15d. El sistema elimina el escenario y actualiza la lista.
* **A3: Escenario no editable por permisos**
    * 16a. El sistema detecta que el docente no tiene permisos para editar un escenario institucional.
    * 16b. El sistema bloquea la edición y muestra mensaje informativo.

---

### CU-04: Ejecutar y Enviar Eventos de Simulación
* **Actor principal:** Administrador / Docente
* **Actores secundarios:** Sistema de correo simulado / Base de Datos
* **Descripción:** Permite enviar simulaciones masivas o individuales a estudiantes, usando enlaces únicos de rastreo.
* **Precondiciones:** Deben existir escenarios de phishing creados.
* **Postcondiciones:** La simulación queda enviada y registrada como evento activo.
* **RF asociado:** RF-05

**Flujo principal**
1. El administrador o docente inicia sesión.
2. El sistema muestra el panel del actor.
3. El actor selecciona “Enviar simulación de phishing”.
4. El sistema muestra la lista de escenarios disponibles.
5. El actor selecciona un escenario.
6. El sistema solicita seleccionar destinatarios (grupo o estudiante individual).
7. El actor selecciona el grupo o lista de estudiantes.
8. El sistema muestra una vista previa del correo simulado.
9. El actor confirma el envío.
10. El sistema genera enlaces únicos de rastreo para cada estudiante.
11. El sistema registra el evento de simulación en la base de datos.
12. El sistema envía el correo simulado a los estudiantes seleccionados.
13. El sistema confirma que la simulación fue enviada exitosamente.
14. El sistema muestra el estado del evento como “Activo”.
15. El actor revisa el resumen del envío y finaliza el proceso.

**Flujos alternos**
* **A1: No hay estudiantes seleccionados**
    * 7a. El actor no selecciona destinatarios.
    * 7b. El sistema bloquea el envío y muestra alerta.
* **A2: Error al enviar correos**
    * 12a. El sistema detecta fallo en el servidor de correo simulado.
    * 12b. El sistema registra el error.
    * 12c. El sistema muestra mensaje “envío fallido”.
    * 12d. El actor puede reintentar.
* **A3: Escenario inválido**
    * 5a. El sistema detecta escenario incompleto.
    * 5b. El sistema bloquea el envío y solicita corrección.

---

### CU-05: Interactuar con Simulación y Recibir Feedback IA
* **Actor principal:** Estudiante
* **Actores secundarios:** Motor IA / Sistema
* **Descripción:** Permite que el estudiante interactúe con un correo simulado y reciba retroalimentación inmediata al cometer errores.
* **Precondiciones:** Debe existir una simulación activa asignada al estudiante.
* **Postcondiciones:** Se registra el comportamiento del estudiante y se genera retroalimentación educativa.
* **RF asociado:** RF-06, RF-07

**Flujo principal**
1. El estudiante inicia sesión.
2. El sistema muestra el panel del estudiante.
3. El sistema notifica que existe una simulación activa.
4. El estudiante selecciona la opción “Ver simulación”.
5. El sistema muestra el correo simulado en pantalla.
6. El estudiante analiza el remitente, asunto y contenido del correo.
7. El estudiante selecciona una acción (clic en enlace, reportar, ignorar).
8. El sistema registra la acción en tiempo real.
9. El sistema registra fecha, hora y tipo de interacción.
10. Si el estudiante hace clic en un enlace, el sistema registra el clic.
11. Si el estudiante intenta ingresar credenciales, el sistema registra el intento (sin almacenar contraseñas reales).
12. El sistema envía los datos de interacción al motor de análisis IA.
13. El motor IA identifica la vulnerabilidad detectada.
14. El sistema genera retroalimentación inmediata en pantalla.
15. El sistema muestra un mensaje educativo explicando el error cometido.
16. El sistema muestra recomendaciones preventivas.
17. El sistema calcula un puntaje para esa simulación.
18. El sistema guarda el resultado en el historial del estudiante.
19. El estudiante confirma el mensaje y finaliza la simulación.
20. El sistema redirige al panel principal del estudiante.

**Flujos alternos**
* **A1: Estudiante ignora la simulación**
    * 7a. El estudiante decide no interactuar y cierra el correo.
    * 7b. El sistema registra la acción como comportamiento seguro.
    * 7c. El sistema genera retroalimentación positiva.
* **A2: El estudiante cae en phishing ingresando datos**
    * 11a. El sistema detecta intento de ingreso de credenciales.
    * 11b. El sistema bloquea el envío real de datos.
    * 11c. El sistema muestra alerta inmediata educativa.
    * 11d. El sistema registra alta vulnerabilidad.
* **A3: Fallo del motor IA**
    * 12a. El sistema no logra comunicarse con el motor IA.
    * 12b. El sistema muestra retroalimentación genérica predefinida.
    * 12c. El sistema registra el evento para auditoría.

---

### CU-06: Consultar Métricas y Estadísticas de Rendimiento
* **Actor principal:** Docente / Administrador
* **Actores secundarios:** Sistema / Base de Datos
* **Descripción:** Permite consultar reportes estadísticos visuales por grupo, nivel y tipo de ataque.
* **Precondiciones:** Deben existir simulaciones ejecutadas.
* **Postcondiciones:** Reporte mostrado o exportado.
* **RF asociado:** RF-08

**Flujo principal**
1. El docente o administrador inicia sesión.
2. El sistema muestra el dashboard correspondiente.
3. El actor selecciona “Panel de Analítica Institucional”.
4. El sistema carga estadísticas generales del sistema.
5. El actor selecciona un filtro por grupo o facultad.
6. El sistema consulta los registros asociados.
7. El sistema muestra indicadores como tasa de clics y tasa de ingreso de datos.
8. El sistema muestra gráficos comparativos por nivel de vulnerabilidad.
9. El actor selecciona un rango de fechas.
10. El sistema recalcula estadísticas según el rango.
11. El actor selecciona un escenario específico para ver detalles.
12. El sistema muestra estadísticas por tipo de ataque.
13. El actor selecciona “Exportar reporte”.
14. El sistema genera un reporte visual.
15. El sistema entrega el archivo descargable al actor.
16. El sistema registra la exportación para control interno.

**Flujos alternos**
* **A1: No existen datos suficientes**
    * 6a. El sistema no encuentra registros para los filtros aplicados.
    * 6b. El sistema muestra mensaje “no hay datos disponibles”.
* **A2: Error al exportar**
    * 14a. El sistema no puede generar el archivo.
    * 14b. El sistema muestra mensaje de error y recomienda reintentar.
* **A3: Docente sin acceso al grupo**
    * 5a. El docente intenta acceder a un grupo no asignado.
    * 5b. El sistema bloquea acceso y muestra mensaje de restricción.

---

### CU-07: Gestionar Usuarios y Grupos
* **Actor principal:** Administrador / Docente
* **Actores secundarios:** Base de Datos
* **Descripción:** Permite administrar docentes, estudiantes y agrupación académica para campañas focalizadas.
* **Precondiciones:** Administrador autenticado o docente con permisos asignados.
* **Postcondiciones:** Usuarios creados y grupos configurados.
* **RF asociado:** RF-13

**Flujo principal**
1. El administrador inicia sesión.
2. El sistema muestra el panel administrativo.
3. El administrador selecciona “Gestión de Usuarios”.
4. El sistema muestra lista de usuarios existentes.
5. El administrador selecciona “Crear cuenta docente”.
6. El sistema muestra formulario de creación.
7. El administrador ingresa datos del docente.
8. El administrador confirma creación.
9. El sistema valida el correo institucional.
10. El sistema crea la cuenta docente y asigna rol correspondiente.
11. El sistema confirma la creación y muestra el docente en la lista.
12. El docente inicia sesión posteriormente en el sistema.
13. El docente selecciona “Gestionar grupos”.
14. El sistema muestra los grupos actuales del docente.
15. El docente selecciona “Crear nuevo grupo”.
16. El sistema solicita nombre del grupo y estudiantes asociados.
17. El docente selecciona estudiantes desde una lista disponible.
18. El sistema registra el grupo y asigna los estudiantes al docente.
19. El sistema confirma creación del grupo.
20. El sistema permite al docente seleccionar el grupo para futuras simulaciones.

**Flujos alternos**
* **A1: Correo inválido**
    * 9a. El sistema detecta correo no institucional.
    * 9b. El sistema rechaza la creación del docente.
* **A2: Estudiante ya asignado a otro grupo**
    * 17a. El sistema detecta que el estudiante pertenece a otro grupo.
    * 17b. El sistema notifica al docente.
    * 17c. El docente decide mantenerlo o reasignarlo según permisos.
* **A3: Docente intenta crear cuentas**
    * 5a. El docente intenta acceder a creación de docentes.
    * 5b. El sistema bloquea la opción por falta de permisos.
