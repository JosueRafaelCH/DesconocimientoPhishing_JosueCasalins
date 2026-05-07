-- ============================================================
-- CREACIÓN DE BASE DE DATOS
-- ============================================================
CREATE DATABASE IF NOT EXISTS phishing_simulator
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
 
USE phishing_simulator;
 
-- ============================================================
-- 1. CATÁLOGOS DE NORMALIZACIÓN
-- ============================================================
 
CREATE TABLE Roles (
  id_rol      INT          NOT NULL AUTO_INCREMENT,
  nombre_rol  VARCHAR(50)  NOT NULL COMMENT 'Ej: Admin, Docente, Estudiante',
  CONSTRAINT pk_roles PRIMARY KEY (id_rol)
) COMMENT = 'Catálogo de roles del sistema';
 
-- ------------------------------------------------------------
CREATE TABLE Estados_Usuario (
  id_estado     INT          NOT NULL AUTO_INCREMENT,
  nombre_estado VARCHAR(50)  NOT NULL COMMENT 'Ej: Activo, Inactivo, Bloqueado',
  CONSTRAINT pk_estados_usuario PRIMARY KEY (id_estado)
) COMMENT = 'Catálogo de estados de actividad de un usuario';
 
-- ------------------------------------------------------------
CREATE TABLE Estados_Evento (
  id_estado_evento  INT          NOT NULL AUTO_INCREMENT,
  nombre_estado     VARCHAR(50)  NOT NULL COMMENT 'Ej: Enviado, Abierto, Ignorado',
  CONSTRAINT pk_estados_evento PRIMARY KEY (id_estado_evento)
) COMMENT = 'Catálogo de estados de un evento de simulación';
 
-- ------------------------------------------------------------
CREATE TABLE Estratos (
  id_estrato   INT          NOT NULL COMMENT 'Valores válidos: 1 al 6',
  descripcion  VARCHAR(50)  NOT NULL COMMENT 'Ej: Estrato 1, Estrato 2',
  CONSTRAINT pk_estratos       PRIMARY KEY (id_estrato),
  CONSTRAINT chk_estrato_rango CHECK (id_estrato BETWEEN 1 AND 6)
) COMMENT = 'Catálogo de estratos socioeconómicos (Colombia)';
 
-- ============================================================
-- 2. MÓDULO DE USUARIOS
-- ============================================================
 
CREATE TABLE Usuarios (
  id_usuario            INT           NOT NULL AUTO_INCREMENT,
  nombres               VARCHAR(100)  NOT NULL,
  apellidos             VARCHAR(100)  NOT NULL,
  correo_institucional  VARCHAR(150)  NOT NULL,
  contrasena_hash       VARCHAR(255)  NOT NULL COMMENT 'Nunca texto plano',
  id_rol                INT           NOT NULL,
  id_estado             INT           NOT NULL,
  id_estrato            INT           NOT NULL,
  fecha_registro        DATETIME      NOT NULL DEFAULT (NOW()),
  fecha_actualizacion   DATETIME      NOT NULL DEFAULT (NOW()),
  CONSTRAINT pk_usuarios              PRIMARY KEY (id_usuario),
  CONSTRAINT uq_correo_institucional  UNIQUE      (correo_institucional),
  CONSTRAINT fk_usuarios_rol          FOREIGN KEY (id_rol)     REFERENCES Roles          (id_rol),
  CONSTRAINT fk_usuarios_estado       FOREIGN KEY (id_estado)  REFERENCES Estados_Usuario (id_estado),
  CONSTRAINT fk_usuarios_estrato      FOREIGN KEY (id_estrato) REFERENCES Estratos        (id_estrato)
) COMMENT = 'Usuarios del sistema: admins, docentes y estudiantes';
 
-- ============================================================
-- 3. MÓDULO DE NIVELES (LÓGICA ADAPTATIVA)
-- ============================================================
 
CREATE TABLE Niveles_Dificultad (
  id_nivel        INT           NOT NULL AUTO_INCREMENT,
  nombre_nivel    VARCHAR(50)   NOT NULL COMMENT 'Ej: Fácil, Medio, Difícil',
  puntaje_minimo  DECIMAL(5,2)  NOT NULL COMMENT 'Puntaje mínimo (inclusive) para este nivel',
  puntaje_maximo  DECIMAL(5,2)  NOT NULL COMMENT 'Puntaje máximo (inclusive) para este nivel',
  descripcion     TEXT              NULL,
  CONSTRAINT pk_niveles_dificultad   PRIMARY KEY (id_nivel),
  CONSTRAINT chk_rango_puntaje       CHECK (puntaje_minimo <= puntaje_maximo)
) COMMENT = 'Niveles de dificultad con rangos de puntaje para asignación adaptativa';
 
-- ============================================================
-- 4. MÓDULO EVALUATIVO
-- ============================================================
 
CREATE TABLE Test_Evaluativo (
  id_test             INT       NOT NULL AUTO_INCREMENT,
  id_usuario          INT       NOT NULL,
  fecha_realizacion   DATETIME  NOT NULL DEFAULT (NOW()),
  fecha_actualizacion DATETIME  NOT NULL DEFAULT (NOW()),
  CONSTRAINT pk_test_evaluativo  PRIMARY KEY (id_test),
  CONSTRAINT fk_test_usuario     FOREIGN KEY (id_usuario) REFERENCES Usuarios (id_usuario)
) COMMENT = 'Sesiones de test realizadas por un usuario. El puntaje se calcula dinámicamente.';
 
-- ------------------------------------------------------------
CREATE TABLE Preguntas_Test (
  id_pregunta         INT       NOT NULL AUTO_INCREMENT,
  enunciado           TEXT      NOT NULL,
  id_nivel            INT       NOT NULL,
  fecha_actualizacion DATETIME  NOT NULL DEFAULT (NOW()),
  CONSTRAINT pk_preguntas_test  PRIMARY KEY (id_pregunta),
  CONSTRAINT fk_pregunta_nivel  FOREIGN KEY (id_nivel) REFERENCES Niveles_Dificultad (id_nivel)
) COMMENT = 'Banco de preguntas del test evaluativo clasificadas por nivel';
 
-- ------------------------------------------------------------
CREATE TABLE Opciones_Pregunta (
  id_opcion     INT           NOT NULL AUTO_INCREMENT,
  id_pregunta   INT           NOT NULL,
  texto_opcion  VARCHAR(500)  NOT NULL,
  es_correcta   BOOLEAN       NOT NULL DEFAULT FALSE COMMENT 'Solo una opción por pregunta debe ser TRUE',
  CONSTRAINT pk_opciones_pregunta  PRIMARY KEY (id_opcion),
  CONSTRAINT fk_opcion_pregunta    FOREIGN KEY (id_pregunta) REFERENCES Preguntas_Test (id_pregunta)
) COMMENT = 'Opciones de respuesta por pregunta. Creada para cumplir 1FN.';
 
-- ------------------------------------------------------------
CREATE TABLE Respuestas_Test (
  id_respuesta  INT  NOT NULL AUTO_INCREMENT,
  id_test       INT  NOT NULL,
  id_pregunta   INT  NOT NULL,
  id_opcion     INT  NOT NULL COMMENT 'El texto de la opción se obtiene desde Opciones_Pregunta.texto_opcion',
  CONSTRAINT pk_respuestas_test     PRIMARY KEY (id_respuesta),
  CONSTRAINT fk_respuesta_test      FOREIGN KEY (id_test)     REFERENCES Test_Evaluativo  (id_test),
  CONSTRAINT fk_respuesta_pregunta  FOREIGN KEY (id_pregunta) REFERENCES Preguntas_Test   (id_pregunta),
  CONSTRAINT fk_respuesta_opcion    FOREIGN KEY (id_opcion)   REFERENCES Opciones_Pregunta (id_opcion)
) COMMENT = 'Opción seleccionada por usuario en cada pregunta. Sin datos duplicados.';
 
-- ============================================================
-- 5. MÓDULO DE EJECUCIÓN DE SIMULACIONES
-- ============================================================
 
CREATE TABLE Escenarios_Phishing (
  id_escenario        INT           NOT NULL AUTO_INCREMENT,
  titulo              VARCHAR(150)  NOT NULL,
  descripcion         VARCHAR(500)      NULL,
  imagen_escenario    VARCHAR(300)      NULL COMMENT 'Ruta o URL. Regla: exactamente una imagen por escenario.',
  id_nivel            INT           NOT NULL,
  fecha_actualizacion DATETIME      NOT NULL DEFAULT (NOW()),
  CONSTRAINT pk_escenarios_phishing  PRIMARY KEY (id_escenario),
  CONSTRAINT fk_escenario_nivel      FOREIGN KEY (id_nivel) REFERENCES Niveles_Dificultad (id_nivel)
) COMMENT = 'Escenarios de phishing simulado asignados según nivel del estudiante';
 
-- ------------------------------------------------------------
CREATE TABLE Eventos_Simulacion (
  id_evento           INT       NOT NULL AUTO_INCREMENT,
  id_usuario          INT       NOT NULL,
  id_escenario        INT       NOT NULL,
  id_test             INT       NOT NULL,
  id_estado_evento    INT       NOT NULL,
  fecha_envio         DATETIME  NOT NULL DEFAULT (NOW()),
  fecha_actualizacion DATETIME  NOT NULL DEFAULT (NOW()),
  CONSTRAINT pk_eventos_simulacion      PRIMARY KEY (id_evento),
  CONSTRAINT fk_evento_usuario          FOREIGN KEY (id_usuario)       REFERENCES Usuarios           (id_usuario),
  CONSTRAINT fk_evento_escenario        FOREIGN KEY (id_escenario)     REFERENCES Escenarios_Phishing (id_escenario),
  CONSTRAINT fk_evento_test             FOREIGN KEY (id_test)          REFERENCES Test_Evaluativo    (id_test),
  CONSTRAINT fk_evento_estado           FOREIGN KEY (id_estado_evento) REFERENCES Estados_Evento     (id_estado_evento)
) COMMENT = 'Registro del envío de un escenario de phishing a un usuario';
 
-- ============================================================
-- 6. MÓDULO DE INTERACCIÓN Y FEEDBACK IA
-- ============================================================
 
CREATE TABLE Interacciones_Phishing (
  id_interaccion          INT       NOT NULL AUTO_INCREMENT,
  id_evento               INT       NOT NULL,
  fecha_clic              DATETIME      NULL COMMENT 'NULL = no hizo clic',
  fecha_datos_ingresados  DATETIME      NULL COMMENT 'NULL = no ingresó credenciales',
  CONSTRAINT pk_interacciones_phishing  PRIMARY KEY (id_interaccion),
  CONSTRAINT uq_interaccion_evento      UNIQUE      (id_evento),
  CONSTRAINT fk_interaccion_evento      FOREIGN KEY (id_evento) REFERENCES Eventos_Simulacion (id_evento)
) COMMENT = 'Comportamiento del estudiante frente al escenario: clic y/o ingreso de datos';
 
-- ------------------------------------------------------------
CREATE TABLE Feedback_IA (
  id_feedback         INT       NOT NULL AUTO_INCREMENT,
  id_interaccion      INT       NOT NULL,
  contenido_feedback  TEXT      NOT NULL,
  fecha_generacion    DATETIME  NOT NULL DEFAULT (NOW()),
  modelo_ia           VARCHAR(100)  NULL COMMENT 'Trazabilidad del modelo usado',
  CONSTRAINT pk_feedback_ia        PRIMARY KEY (id_feedback),
  CONSTRAINT fk_feedback_interaccion FOREIGN KEY (id_interaccion) REFERENCES Interacciones_Phishing (id_interaccion)
) COMMENT = 'Consejo personalizado generado por la IA para cada interacción';
 
-- ============================================================
-- DATOS SEMILLA (CATÁLOGOS)
-- ============================================================
 
INSERT INTO Roles (nombre_rol) VALUES
  ('Admin'),
  ('Docente'),
  ('Estudiante');
 
INSERT INTO Estados_Usuario (nombre_estado) VALUES
  ('Activo'),
  ('Inactivo'),
  ('Bloqueado');
 
INSERT INTO Estados_Evento (nombre_estado) VALUES
  ('Enviado'),
  ('Abierto'),
  ('Ignorado');
 
INSERT INTO Estratos (id_estrato, descripcion) VALUES
  (1, 'Estrato 1'),
  (2, 'Estrato 2'),
  (3, 'Estrato 3'),
  (4, 'Estrato 4'),
  (5, 'Estrato 5'),
  (6, 'Estrato 6');
 
INSERT INTO Niveles_Dificultad (nombre_nivel, puntaje_minimo, puntaje_maximo, descripcion) VALUES
  ('Fácil',  0.00,  4.99, 'Escenarios básicos con señales de phishing evidentes.'),
  ('Medio',  5.00,  7.99, 'Escenarios con señales moderadamente sutiles.'),
  ('Difícil',8.00, 10.00, 'Escenarios avanzados con spear phishing y contexto personalizado.');
 
-- ============================================================
-- CONSULTAS DE UTILIDAD
-- ============================================================
 
-- Calcular puntaje de un test
-- SELECT COUNT(*) AS puntaje
-- FROM Respuestas_Test rt
-- JOIN Opciones_Pregunta op ON rt.id_opcion = op.id_opcion
-- WHERE rt.id_test = ? AND op.es_correcta = TRUE;
 
-- Determinar nivel según puntaje
-- SELECT id_nivel, nombre_nivel
-- FROM Niveles_Dificultad
-- WHERE ? BETWEEN puntaje_minimo AND puntaje_maximo;
 
-- Ver texto de la opción elegida por el usuario (sin duplicar dato)
-- SELECT op.texto_opcion, op.es_correcta
-- FROM Respuestas_Test rt
-- JOIN Opciones_Pregunta op ON rt.id_opcion = op.id_opcion
-- WHERE rt.id_test = ? AND rt.id_pregunta = ?;
