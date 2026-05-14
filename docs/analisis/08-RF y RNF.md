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

