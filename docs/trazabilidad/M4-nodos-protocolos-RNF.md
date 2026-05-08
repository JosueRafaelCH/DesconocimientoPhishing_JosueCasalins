### Matriz de trazabilidad: Nodos de despliegue → Protocolos → RNF → Componentes

| Nodos de despliegue | Protocolos | RNF (Requisitos No Funcionales) | Componentes |
| :--- | :--- | :--- | :--- |
| Dispositivo del Cliente (Navegador Web) | HTTPS, WSS | RNF03 (Usabilidad)<br><br>RNF04 (Rendimiento < 3s)<br><br>RNF07 (Compatibilidad navegadores)<br><br>RNF08 (Accesibilidad WCAG 2.1) | Componente Frontend (Vistas HTML/CSS, Bootstrap/Tailwind), Controladores de Interfaz de Usuario, Dashboards. |
| Servidor de Aplicaciones (Entorno Cloud) | HTTP/REST, TCP/IP | RNF01 (Seguridad contraseñas bcrypt)<br><br>RNF05 (Escalabilidad 500 usuarios)<br><br>RNF09 (Mantenibilidad / Arquitectura MVC)<br><br>RNF11 (Portabilidad nube AWS/Azure)<br><br>RNF12 (Auditabilidad / Logs) | API REST (Spring Boot), Módulo de Seguridad (Auth), Módulo Motor IA y Simulación, Generador de Reportes (JasperReports). |
| Servidor de Base de Datos (Relacional) | JDBC, TCP/IP | RNF02 (Protección de datos simulados)<br><br>RNF06 (Disponibilidad 99%)<br><br>RNF10 (Privacidad y anonimización) | Motor SQL, Esquema Relacional (Tablas), Procedimientos Almacenados, Logs de transacciones. |
