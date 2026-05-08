# Proyecto Framework

Repositorio con documentación y artefactos del proyecto.

## Estructura
- **src/**: Código fuente del backend y frontend.
- **docs/**: Documentación detallada del análisis y diseño.
- **docs/imagenes/**: Evidencias, capturas de pantalla y diagramas (Ishikawa, MER, etc.).
- **docs/trazabilidad/**: Matrices de trazabilidad que vinculan RF, CU y componentes.

---

## Preguntas del Proyecto

### ¿Qué módulo implementas?
Implemento el **Módulo de Simulación Adaptativa y Concientización en Ciberseguridad**. Este módulo es el núcleo del sistema y se encarga de gestionar el ciclo completo de aprendizaje del estudiante: desde el test diagnóstico inicial para medir el nivel de vulnerabilidad, hasta la ejecución de escenarios de phishing personalizados y la generación de retroalimentación inmediata mediante el motor de análisis.

### ¿Qué tablas cubre tu módulo?
Mi implementación cubre la totalidad del esquema relacional diseñado para el simulador, el cual incluye 13 tablas organizadas en los siguientes módulos:
* **Usuarios y Acceso:** `Usuarios`, `Roles`, `Estados_Usuario` y `Estratos`.
* **Lógica Adaptativa:** `Niveles_Dificultad`.
* **Evaluación:** `Test_Evaluativo`, `Preguntas_Test`, `Opciones_Pregunta` y `Respuestas_Test`.
* **Simulación e Interacción:** `Escenarios_Phishing`, `Eventos_Simulacion`, `Interacciones_Phishing` y `Feedback_IA`.

### ¿Qué framework elegiste y por qué?
Elegí **Spring Boot (Java)** como framework principal para el backend. La elección se basa en su robustez para manejar arquitecturas modulares (MVC) y su ecosistema integral para la seguridad. Al ser un proyecto que maneja autenticación institucional y datos de desempeño, Spring Boot me facilita la integración de **Spring Security** para el cifrado de contraseñas con BCrypt y **Spring Data JPA/Hibernate** para una gestión eficiente y segura de la persistencia de datos en MySQL, garantizando la escalabilidad necesaria para soportar múltiples usuarios simultáneos.

### ¿Cómo ejecutar el proyecto?
Para poner en marcha el simulador en un entorno local, sigo estos pasos:

1.  **Configuración de la Base de Datos:**
    * Importo el script DDL proporcionado en la documentación para crear la base de datos `phishing_simulator` en MySQL.
    * Aseguro que los datos semilla (catálogos de roles, estratos y niveles) estén cargados.

2.  **Configuración del Entorno:**
    * Clono el repositorio y me dirijo al archivo `src/main/resources/application.properties`.
    * Actualizo las credenciales de conexión (`spring.datasource.username` y `spring.datasource.password`) según mi configuración local de MySQL.

3.  **Ejecución del Backend:**
    * Desde la terminal, en la raíz del proyecto, ejecuto el comando:
      ```bash
      mvn spring-boot:run
      ```
    * O bien, inicio el proyecto desde mi IDE (IntelliJ IDEA o Eclipse) ejecutando la clase principal del framework.

4.  **Acceso:**
    * Una vez que el servidor esté arriba, accedo a la interfaz web a través de `http://localhost:8080` para iniciar el registro.
