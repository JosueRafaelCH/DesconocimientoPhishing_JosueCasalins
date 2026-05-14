# Casos de Uso del Simulador

## CU-01A: Registrar Usuario

* **Actor principal:** Estudiante / Docente
* **Actores secundarios:** Sistema, Base de Datos
* **Descripción:** Permite a un nuevo usuario crear una cuenta en la plataforma utilizando su correo institucional.
* **Precondiciones:** El usuario no debe tener una cuenta activa con el correo ingresado.
* **Postcondiciones:** El usuario queda registrado en el sistema con un rol predeterminado y sus credenciales encriptadas.
* **Requisitos Funcionales (RF):** RF01 (Registro con correo institucional), RF02 (Autenticación segura - políticas de contraseña).
* **Requisitos No Funcionales (RNF):** RNF01 (Seguridad de contraseñas), RNF03 (Usabilidad), RNF04 (Rendimiento), RNF12 (Auditabilidad).

**Flujo principal:**
1. El usuario accede a la plataforma web y selecciona “Registrarse”.
2. El sistema muestra el formulario de registro.
3. El usuario ingresa nombre completo, correo institucional (ej. dominio `@itm.edu.co`) y contraseña.
4. El sistema valida que el correo pertenezca a la institución y no esté registrado previamente.
5. El sistema valida que la contraseña cumpla con las políticas de seguridad (mínimo 8 caracteres, una mayúscula, un número y un símbolo).
6. El sistema almacena al usuario en la base de datos y asigna el rol correspondiente (por defecto Estudiante).
7. El sistema muestra confirmación de registro exitoso y redirige al inicio de sesión.

**Flujos alternos:**
* **A1: Correo no institucional o ya registrado:** (Paso 4) El sistema detecta que el dominio no es válido o ya existe. Muestra el mensaje "Correo inválido o ya en uso" y detiene el proceso.
* **A2: Contraseña insegura:** (Paso 5) El sistema detecta que la contraseña no cumple la longitud o complejidad. Muestra los requisitos mínimos y solicita un nuevo ingreso.

---

## CU-01B: Autenticar Usuario

* **Actor principal:** Estudiante / Docente / Administrador
* **Actores secundarios:** Sistema, Base de Datos
* **Descripción:** Permite a un usuario registrado acceder a su panel correspondiente validando sus credenciales.
* **Precondiciones:** El usuario debe estar registrado previamente.
* **Postcondiciones:** Se crea una sesión activa y el usuario es redirigido a su dashboard.
* **Requisitos Funcionales (RF):** RF02 (Autenticación segura).
* **Requisitos No Funcionales (RNF):** RNF01 (Seguridad de contraseñas), RNF03 (Usabilidad), RNF04 (Rendimiento), RNF12 (Auditabilidad).

**Flujo principal:**
1. El usuario selecciona "Iniciar Sesión" e ingresa correo y contraseña.
2. El sistema encripta la contraseña ingresada y la compara con la almacenada en la base de datos.
3. El sistema identifica el rol asociado a la cuenta.
4. El sistema crea la sesión activa y redirige al dashboard según el rol.

**Flujos alternos:**
* **A1: Credenciales inválidas:** (Paso 2) El sistema detecta discrepancia. Muestra "Correo o contraseña incorrectos" y aumenta el contador de intentos fallidos.
* **A2: Cuenta bloqueada:** (Paso 2) Si el usuario supera el límite de intentos fallidos, el sistema bloquea temporalmente la cuenta y muestra "Cuenta bloqueada por seguridad".
* **A3: Usuario sin rol asignado:** (Paso 3) El sistema no encuentra rol. Bloquea el acceso y notifica al administrador.

---

## CU-02: Realizar Test Diagnóstico

* **Actor principal:** Estudiante
* **Actores secundarios:** Sistema
* **Descripción:** Permite al estudiante realizar una prueba inicial para medir su nivel de conocimiento y recibir una clasificación de vulnerabilidad, sin lógica de aprobación o reprobación.
* **Precondiciones:** El estudiante debe tener una sesión activa y no haber completado el test previamente.
* **Postcondiciones:** El estudiante obtiene un nivel asignado (Fácil, Medio, Difícil) registrado en su perfil.
* **Requisitos Funcionales (RF):** RF04 (Ejecución de simulaciones), RF07 (Registro de desempeño).
* **Requisitos No Funcionales (RNF):** RNF02 (Protección de datos), RNF03 (Usabilidad), RNF04 (Rendimiento).

**Flujo principal:**
1. El estudiante selecciona “Realizar Test Diagnóstico” en su dashboard.
2. El sistema muestra las instrucciones y advierte que el test busca medir el conocimiento actual, no calificar.
3. El estudiante confirma el inicio.
4. El sistema carga el banco de preguntas y presenta la primera.
5. El estudiante selecciona una respuesta.
6. El sistema registra la respuesta y carga la siguiente (se repite hasta terminar).
7. El estudiante selecciona “Finalizar Test”.
8. El sistema valida que todas las preguntas tengan respuesta.
9. El sistema calcula el puntaje para determinar el nivel de vulnerabilidad y lo actualiza en el perfil.
10. El sistema muestra el nivel obtenido y redirige al dashboard.

**Flujos alternos:**
* **A1: Preguntas sin responder:** (Paso 8) El sistema muestra alerta de preguntas faltantes y permite completarlas.
* **A2: Abandono del test / Tiempo excedido:** (Paso 6) Si el estudiante cierra la sesión o excede el tiempo límite establecido (ej. 30 min), el sistema guarda el progreso parcial. Al retomar, el estudiante continúa desde donde lo dejó.

---

## CU-03: Gestionar Escenarios de Phishing

* **Actor principal:** Administrador / Docente
* **Actores secundarios:** Sistema, Base de Datos
* **Descripción:** Permite crear, editar, eliminar y listar las plantillas de correos simulados sin usar datos reales.
* **Precondiciones:** El usuario debe tener permisos de Docente o Administrador.
* **Postcondiciones:** Los cambios en el catálogo de escenarios se reflejan en la base de datos.
* **Requisitos Funcionales (RF):** RF04 (Ejecución de simulaciones - Gestión de datos ficticios).
* **Requisitos No Funcionales (RNF):** RNF03 (Usabilidad), RNF12 (Auditabilidad).

**Flujo principal:**
1. El actor accede a “Gestionar Escenarios de Phishing”.
2. El sistema muestra la lista de escenarios existentes.
3. El actor selecciona "Crear nuevo escenario" y completa: remitente falso, asunto, cuerpo, URL simulada y nivel de dificultad.
4. El actor selecciona "Guardar".
5. El sistema valida el formato de la URL simulada y que los campos obligatorios no estén vacíos.
6. El sistema registra la plantilla y actualiza la lista visible.

**Flujos alternos:**
* **A1: Formato de URL inválido:** (Paso 5) El sistema detecta que el enlace falso no tiene un formato válido (ej. falta HTTP/HTTPS). Bloquea el guardado y exige corrección.
* **A2: Edición/Eliminación:** (Paso 2) El actor selecciona un escenario existente. Modifica los datos o solicita eliminarlo. El sistema actualiza o borra el registro tras confirmación.
* **A3: Permisos insuficientes para editar:** (Paso 2) Si el docente intenta modificar un escenario global creado por el Administrador, el sistema lo bloquea en modo "Solo lectura".

---

## CU-04: Desplegar Simulación de Phishing

* **Actor principal:** Administrador / Docente
* **Actores secundarios:** Sistema de correo simulado, Base de Datos
* **Descripción:** Permite enviar una plantilla de phishing a un grupo de estudiantes para campañas, generando enlaces de rastreo individuales.
* **Precondiciones:** Debe existir al menos un escenario creado y un grupo con estudiantes asignados.
* **Postcondiciones:** La simulación cambia a estado "Activo" y los correos son enviados.
* **Requisitos Funcionales (RF):** RF10 (Campañas institucionales).
* **Requisitos No Funcionales (RNF):** RNF02 (Protección de datos), RNF04 (Rendimiento), RNF05 (Escalabilidad), RNF12 (Auditabilidad).

**Flujo principal:**
1. El actor selecciona “Enviar simulación de phishing”.
2. El sistema muestra escenarios y grupos disponibles.
3. El actor selecciona un escenario y los destinatarios.
4. El sistema muestra una vista previa.
5. El actor confirma el envío.
6. El sistema genera identificadores únicos de rastreo en la URL para cada estudiante.
7. El sistema envía los correos simulados y cambia el estado del evento a "Activo".

**Flujos alternos:**
* **A1: Estudiantes con simulación ya activa:** (Paso 6) El sistema detecta que algunos destinatarios ya tienen ese mismo escenario en curso. Los excluye del envío para evitar duplicidad y notifica al actor.
* **A2: Fallo en el servidor de correo:** (Paso 7) El sistema no logra conectarse al servicio de mensajería (o bandeja interna). Registra el estado como "Fallido" y permite reintentar.

---

## CU-05: Procesar Interacción de Simulación

* **Actor principal:** Estudiante
* **Actores secundarios:** Motor IA, Sistema
* **Descripción:** Captura las acciones del estudiante frente a un correo simulado y provee retroalimentación en tiempo real.
* **Precondiciones:** El estudiante debe interactuar con un enlace rastreable de una simulación activa.
* **Postcondiciones:** La acción se almacena en el historial y la simulación se marca como completada para ese estudiante.
* **Requisitos Funcionales (RF):** RF04 (Ejecución de simulaciones), RF05 (Retroalimentación inmediata), RF06 (Recomendaciones personalizadas), RF07 (Registro de desempeño), RF09 (Ajuste automático de dificultad).
* **Requisitos No Funcionales (RNF):** RNF02 (Protección de datos), RNF03 (Usabilidad), RNF04 (Rendimiento - tiempo de carga menor a 3s), RNF12 (Auditabilidad).

**Flujo principal:**
1. El estudiante abre el correo simulado y realiza una acción (clic en enlace o intento de login falso).
2. El sistema captura el identificador único del estudiante, la fecha y el tipo de interacción (sin almacenar credenciales reales).
3. El sistema envía los metadatos de la acción al motor de análisis IA.
4. El motor IA evalúa la acción y devuelve una respuesta educativa y recomendaciones.
5. El sistema muestra en pantalla la retroalimentación inmediata detallando la vulnerabilidad explotada y las sugerencias.
6. El sistema guarda el resultado, actualiza el desempeño y ajusta la dificultad futura del estudiante.

**Flujos alternos:**
* **A1: Simulación ignorada por tiempo:** (Paso 1) Si transcurre el tiempo límite de la campaña sin que el estudiante abra el correo, el sistema cierra automáticamente su participación, registrándolo como "Comportamiento seguro (Ignorado)".
* **A2: Alta latencia del Motor IA:** (Paso 4) Si la IA tarda más de 3 segundos en responder, el sistema aborta la petición externa y muestra una retroalimentación genérica predefinida en la base de datos para cumplir con el RNF04.

---

## CU-06: Consultar Métricas de Rendimiento

* **Actor principal:** Docente / Administrador
* **Actores secundarios:** Sistema, Base de Datos
* **Descripción:** Permite visualizar y exportar reportes estadísticos sobre las campañas y vulnerabilidades detectadas.
* **Precondiciones:** Deben existir simulaciones ejecutadas con datos recopilados.
* **Postcondiciones:** El actor visualiza los gráficos o descarga el archivo de reporte anonimizado.
* **Requisitos Funcionales (RF):** RF10 (Campañas institucionales - Métricas globales).
* **Requisitos No Funcionales (RNF):** RNF03 (Usabilidad), RNF10 (Privacidad), RNF12 (Auditabilidad).

**Flujo principal:**
1. El actor accede al “Panel de Analítica Institucional”.
2. El sistema consulta la base de datos y carga estadísticas generales consolidadas.
3. El actor aplica filtros (rango de fechas, grupo, nivel de vulnerabilidad).
4. El sistema recalcula y actualiza los gráficos (tasa de clics, ingreso de datos).
5. El actor selecciona "Exportar reporte".
6. El sistema anonimiza los datos personales (RNF10), compila el reporte y lo descarga en el equipo local.

**Flujos alternos:**
* **A1: Ausencia de datos:** (Paso 4) Los filtros no devuelven resultados. El sistema renderiza los gráficos vacíos con el mensaje "No hay datos para los parámetros seleccionados".
* **A2: Restricción de visibilidad:** (Paso 2) Si el actor es un Docente, el sistema restringe automáticamente la consulta a los grupos que tiene asignados, omitiendo datos de otras facultades.

---

## CU-07: Gestionar Grupos de Estudiantes

* **Actor principal:** Docente / Administrador
* **Actores secundarios:** Base de Datos
* **Descripción:** Permite crear agrupaciones lógicas de estudiantes para facilitar el despliegue focalizado de simulaciones en campañas institucionales.
* **Precondiciones:** El docente o administrador debe estar autenticado. Los estudiantes deben existir en el sistema.
* **Postcondiciones:** El grupo se crea y los estudiantes quedan vinculados a él.
* **Requisitos Funcionales (RF):** RF10 (Campañas institucionales - Gestión de públicos).
* **Requisitos No Funcionales (RNF):** RNF03 (Usabilidad), RNF12 (Auditabilidad).

**Flujo principal:**
1. El actor accede a la sección "Gestionar grupos".
2. El sistema muestra los grupos previamente creados por el actor.
3. El actor selecciona "Crear nuevo grupo" y asigna un nombre.
4. El actor busca y selecciona estudiantes desde el directorio del sistema.
5. El sistema valida que los estudiantes seleccionados no pertenezcan ya al mismo grupo.
6. El sistema crea el grupo en la base de datos con las relaciones correspondientes.
7. El sistema confirma la creación.

**Flujos alternos:**
* **A1: Estudiante duplicado:** (Paso 5) Si un estudiante seleccionado ya está en el grupo que se está editando, el sistema ignora la duplicidad silenciosamente y solo vincula los nuevos.
* **A2: Lista vacía:** (Paso 4) El actor intenta guardar el grupo sin haber seleccionado estudiantes. El sistema solicita añadir al menos a un participante.
