### Matriz de trazabilidad: DDL → Modelo relacional → PK → FK → Restricciones

| Comando DDL | Tabla | PK | FK | Restricciones |
| :--- | :--- | :--- | :--- | :--- |
| CREATE TABLE Usuario | Usuario | id_usuario | N/A | NOT NULL (nombre, email, password), UNIQUE (email), CHECK (rol IN ('ESTUDIANTE', 'DOCENTE', 'ADMIN')) |
| CREATE TABLE Escenario_Phishing | Escenario_Phishing | id_escenario | id_admin (en tabla Usuario) | NOT NULL (asunto, remitente_falso, contenido), CHECK (nivel IN ('BASICO', 'INTERMEDIO', 'AVANZADO')) |
| CREATE TABLE Test_Evaluativo | Test_Evaluativo | id_test | id_estudiante (en tabla Usuario) | NOT NULL (puntaje_calculado), DEFAULT CURRENT_TIMESTAMP |
| CREATE TABLE Evento_Simulacion | Evento_Simulacion | id_evento | id_test (en tabla Test_Evaluativo), id_escenario (en tabla Escenario_Phishing) | UNIQUE (id_test), CHECK (estado IN ('ACTIVO', 'ABANDONADO', 'CONSOLIDADO')) |
| CREATE TABLE Evaluacion_IA | Evaluacion_IA | id_evaluacion | id_evento (en tabla Evento_Simulacion) | UNIQUE (id_evento), NOT NULL (feedback_generado) |
| CREATE TABLE Reporte_Hibrido | Reporte_Hibrido | id_reporte | id_evaluacion (en tabla Evaluacion_IA) | UNIQUE (id_evaluacion), DEFAULT TRUE (es_inmutable) |
