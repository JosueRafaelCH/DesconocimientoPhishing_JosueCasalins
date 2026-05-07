## Convenciones

| Símbolo / Abrev. | Significado |
| :--- | :--- |
| **PK** | Primary Key — Clave primaria. Identifica de forma única cada fila. |
| **FK** | Foreign Key — Clave foránea. Referencia a la PK de otra tabla. |
| **UQ** | Unique — Restricción de unicidad. No se permiten valores duplicados. |
| **1:1** | Relación uno a uno entre dos tablas. |
| **—** | Sin restricción de clave especial. |
| **→** | Nota técnica o regla de negocio relacionada con el campo. |

---

## 1. Catálogos de Normalización

### Tabla: Roles
Catálogo de roles posibles dentro del sistema.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_rol | INT | NO | PK | Identificador único del rol. Auto-incremental. |
| nombre_rol | VARCHAR | NO | — | Nombre del rol. Ej: 'Admin', 'Docente', 'Estudiante'. |

### Tabla: Estados_Usuario
Catálogo de estados de actividad de un usuario.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_estado | INT | NO | PK | Identificador único del estado. Auto-incremental. |
| nombre_estado | VARCHAR | NO | — | Nombre del estado. Ej: 'Activo', 'Inactivo', 'Bloqueado'. |

### Tabla: Estados_Evento
Catálogo de estados de un evento de simulación de phishing.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_estado_evento | INT | NO | PK | Identificador único del estado del evento. Auto-incremental. |
| nombre_estado | VARCHAR | NO | — | Nombre del estado. Ej: 'Enviado', 'Abierto', 'Ignorado'. |

### Tabla: Estratos
Catálogo de estratos socioeconómicos válidos del 1 al 6, según clasificación colombiana.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_estrato | INT | NO | PK | Identificador del estrato. Valores del 1 al 6. No auto-incremental. |
| descripcion | VARCHAR | NO | — | Texto descriptivo. Ej: 'Estrato 1', 'Estrato 2'. |

---

## 2. Módulo de Usuarios

### Tabla: Usuarios
Almacena la información de todos los usuarios del sistema: administradores, docentes y estudiantes.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_usuario | INT | NO | PK | Identificador único del usuario. Auto-incremental. |
| nombres | VARCHAR | NO | — | Nombres del usuario. |
| apellidos | VARCHAR | NO | — | Apellidos del usuario. |
| correo_institucional | VARCHAR | NO | UQ | Correo institucional único. Usado como identificador de login. |
| contrasena_hash | VARCHAR | NO | — | Hash de la contraseña. Nunca se almacena en texto plano. |
| id_rol | INT | NO | FK → Roles | Rol del usuario dentro del sistema. |
| id_estado | INT | NO | FK → Estados_Usuario | Estado actual de la cuenta. |
| id_estrato | INT | NO | FK → Estratos | Estrato socioeconómico del usuario. |
| fecha_registro | DATETIME | NO | — | Fecha y hora de creación del registro. Default: now(). |
| fecha_actualizacion | DATETIME | NO | — | Fecha y hora de la última modificación. |

---

## 3. Módulo de Niveles (Lógica Adaptativa)

### Tabla: Niveles_Dificultad
Define los niveles de dificultad del sistema. Los rangos de puntaje permiten que la capa de aplicación asigne el nivel al estudiante sin dependencias transitivas en Test_Evaluativo.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_nivel | INT | NO | PK | Identificador único del nivel. Auto-incremental. |
| nombre_nivel | VARCHAR | NO | — | Nombre del nivel. Ej: 'Fácil', 'Medio', 'Difícil'. |
| puntaje_minimo | DECIMAL | NO | — | Puntaje mínimo (inclusive) para ser asignado a este nivel. |
| puntaje_maximo | DECIMAL | NO | — | Puntaje máximo (inclusive) para ser asignado a este nivel. |
| descripcion | TEXT | SÍ | — | Descripción detallada de las características del nivel. |

---

## 4. Módulo Evaluativo

### Tabla: Test_Evaluativo
Registra cada sesión de test realizada por un usuario. El puntaje se calcula dinámicamente en la capa de aplicación y el nivel se determina cruzando ese puntaje con los rangos de Niveles_Dificultad.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_test | INT | NO | PK | Identificador único del test. Auto-incremental. |
| id_usuario | INT | NO | FK → Usuarios | Usuario que realizó el test. |
| fecha_realizacion | DATETIME | NO | — | Fecha y hora de realización. Default: now(). |
| fecha_actualizacion | DATETIME | NO | — | Fecha y hora de la última modificación. |

### Tabla: Preguntas_Test
Banco de preguntas del test evaluativo. Las opciones de respuesta se almacenan en Opciones_Pregunta para cumplir 1FN.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_pregunta | INT | NO | PK | Identificador único de la pregunta. Auto-incremental. |
| enunciado | TEXT | NO | — | Texto completo de la pregunta. |
| id_nivel | INT | NO | FK → Niveles_Dificultad | Nivel de conocimiento que evalúa esta pregunta. |
| fecha_actualizacion | DATETIME | NO | — | Fecha y hora de la última modificación. |

### Tabla: Opciones_Pregunta
Opciones de respuesta de cada pregunta de selección múltiple. Tabla creada para cumplir 1FN: las opciones no pueden ser atributos repetidos dentro de Preguntas_Test.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_opcion | INT | NO | PK | Identificador único de la opción. Auto-incremental. |
| id_pregunta | INT | NO | FK → Preguntas_Test | Pregunta a la que pertenece esta opción. |
| texto_opcion | VARCHAR(500) | NO | — | Texto de la opción de respuesta. Máx 500 caracteres. |
| es_correcta | BOOLEAN | NO | — | TRUE si esta es la respuesta correcta. Exactamente una opción por pregunta debe tener este valor en TRUE. |

### Tabla: Respuestas_Test
Registra la opción seleccionada por el usuario en cada pregunta del test. La corrección se verifica cruzando id_opcion con Opciones_Pregunta.es_correcta.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_respuesta | INT | NO | PK | Identificador único de la respuesta. Auto-incremental. |
| id_test | INT | NO | FK → Test_Evaluativo | Test al que pertenece la respuesta. |
| id_pregunta | INT | NO | FK → Preguntas_Test | Pregunta que se respondió. |
| id_opcion | INT | NO | FK → Opciones_Pregunta | Opción seleccionada por el usuario. |

---

## 5. Módulo de Ejecución de Simulaciones

### Tabla: Escenarios_Phishing
Contiene los escenarios de phishing simulado que el sistema asigna a los estudiantes según su nivel.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_escenario | INT | NO | PK | Identificador único del escenario. Auto-incremental. |
| titulo | VARCHAR | NO | — | Título del escenario de phishing. |
| descripcion | VARCHAR | SÍ | — | Descripción del contenido y objetivo del escenario. |
| imagen_escenario | VARCHAR | SÍ | — |  |
| id_nivel | INT | NO | FK → Niveles_Dificultad | Nivel de dificultad al que pertenece el escenario. |
| fecha_actualizacion | DATETIME | NO | — | Fecha y hora de la última modificación. |

### Tabla: Eventos_Simulacion
Registra el envío de un escenario de phishing a un usuario. Es el punto de partida de cada simulación.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_evento | INT | NO | PK | Identificador único del evento. Auto-incremental. |
| id_usuario | INT | NO | FK → Usuarios | Usuario al que se le envió la simulación. |
| id_escenario | INT | NO | FK → Escenarios_Phishing | Escenario de phishing asignado. |
| id_test | INT | NO | FK → Test_Evaluativo | Test evaluativo que originó la asignación del nivel. |
| id_estado_evento | INT | NO | FK → Estados_Evento | Estado actual del evento (Enviado / Abierto / Ignorado). |
| fecha_envio | DATETIME | NO | — | Fecha y hora en que se lanzó la simulación. |
| fecha_actualizacion | DATETIME | NO | — | Fecha y hora de la última modificación. |

---

## 6. Módulo de Interacción y Feedback IA

### Tabla: Interacciones_Phishing
Registra el comportamiento del estudiante frente al escenario simulado: si hizo clic y si ingresó credenciales.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_interaccion | INT | NO | PK | Identificador único de la interacción. Auto-incremental. |
| id_evento | INT | NO | FK (1:1) → Eventos_Simulacion | Evento al que corresponde la interacción. Único por evento. |
| fecha_clic | DATETIME | SÍ | — | Fecha y hora del clic en el enlace. NULL si no hizo clic. |
| fecha_datos_ingresados | DATETIME | SÍ | — | Fecha y hora en que ingresó credenciales. NULL si no lo hizo. |

### Tabla: Feedback_IA
Almacena el consejo personalizado generado por la IA para cada interacción del estudiante.

| Campo | Tipo | Nulo | Clave | Descripción |
| :--- | :--- | :--- | :--- | :--- |
| id_feedback | INT | NO | PK | Identificador único del feedback. Auto-incremental. |
| id_interaccion | INT | NO | FK → Interacciones_Phishing | Interacción a la que responde este feedback. |
| contenido_feedback | TEXT | NO | — | Texto del consejo generado por la IA. |
| fecha_generacion | DATETIME | NO | — | Fecha y hora en que se generó el feedback. |
| modelo_ia | VARCHAR | SÍ | — | Nombre del modelo de IA usado. Campo de trazabilidad opcional. |
