package com.example.SimulatorApp.Config;

import com.example.SimulatorApp.Model.Dao.*;
import com.example.SimulatorApp.Model.Entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(UsuarioDAOIface usuarioRepository, RoleDAOIface roleRepository, 
                                   EstadoUsuarioDAOIface estadoRepository, EstratoDAOIface estratoRepository,
                                   NivelDificultadDAOIface nivelRepository,
                                   EstadoEventoDAOIface estadoEventoRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role(null, "Admin", null));
                roleRepository.save(new Role(null, "Docente", null));
                roleRepository.save(new Role(null, "Estudiante", null));
            }
            if (nivelRepository.count() == 0) {
                nivelRepository.save(new NivelDificultad(null, "Fácil", BigDecimal.valueOf(0), BigDecimal.valueOf(60),
                        "Preguntas básicas sobre conceptos generales de phishing y seguridad informática.", null, null));
                nivelRepository.save(new NivelDificultad(null, "Medianamente fácil", BigDecimal.valueOf(61), BigDecimal.valueOf(80),
                        "Situaciones cotidianas de phishing con señales moderadamente ocultas.", null, null));
                nivelRepository.save(new NivelDificultad(null, "Difícil", BigDecimal.valueOf(81), BigDecimal.valueOf(90),
                        "Escenarios complejos con técnicas de phishing avanzadas y señales sutiles.", null, null));
                nivelRepository.save(new NivelDificultad(null, "Nivel profesional", BigDecimal.valueOf(91), BigDecimal.valueOf(100),
                        "Casos reales de alta sofisticación: spear phishing, whaling y ataques multicanal.", null, null));
            }
            if (estadoEventoRepository.count() == 0) {
                estadoEventoRepository.save(new EstadoEvento(null, "Pendiente", null));
                estadoEventoRepository.save(new EstadoEvento(null, "En curso", null));
                estadoEventoRepository.save(new EstadoEvento(null, "Completado", null));
                estadoEventoRepository.save(new EstadoEvento(null, "Cancelado", null));
            }
            if (estadoRepository.count() == 0) {
                estadoRepository.save(new EstadoUsuario(null, "Activo", null));
                estadoRepository.save(new EstadoUsuario(null, "Inactivo", null));
                estadoRepository.save(new EstadoUsuario(null, "Bloqueado", null));
            }
            if (estratoRepository.count() == 0) {
                for (int i = 1; i <= 6; i++) {
                    Estrato e = new Estrato();
                    e.setId(i);
                    e.setDescripcion("Estrato " + i);
                    estratoRepository.save(e);
                }
            }

            Role adminRole = roleRepository.findByNombreRol("Admin");

            if (usuarioRepository.findByCorreoInstitucional("admin@uniremington.edu.co").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombres("Admin");
                admin.setApellidos("Sistema");
                admin.setCorreoInstitucional("admin@uniremington.edu.co");
                admin.setContrasenaHash(passwordEncoder.encode("Admin123!"));
                admin.setRol(adminRole);
                admin.setEstado(estadoRepository.findAll().get(0));
                admin.setEstrato(estratoRepository.findAll().get(0));
                admin.setFechaRegistro(LocalDate.now());
                admin.setFechaActualizacion(LocalDate.now());
                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByCorreoInstitucional("admin2@uniremington.edu.co").isEmpty()) {
                Usuario admin2 = new Usuario();
                admin2.setNombres("Admin2");
                admin2.setApellidos("Seguridad");
                admin2.setCorreoInstitucional("admin2@uniremington.edu.co");
                admin2.setContrasenaHash(passwordEncoder.encode("Admin456!"));
                admin2.setRol(adminRole);
                admin2.setEstado(estadoRepository.findAll().get(0));
                admin2.setEstrato(estratoRepository.findAll().get(0));
                admin2.setFechaRegistro(LocalDate.now());
                admin2.setFechaActualizacion(LocalDate.now());
                usuarioRepository.save(admin2);
            }

            Role docenteRole = roleRepository.findByNombreRol("Docente");

            Usuario docente = null;
            if (usuarioRepository.findByCorreoInstitucional("docente@uniremington.edu.co").isEmpty()) {
                docente = new Usuario();
                docente.setNombres("Carlos");
                docente.setApellidos("Mendoza");
                docente.setCorreoInstitucional("docente@uniremington.edu.co");
                docente.setContrasenaHash(passwordEncoder.encode("Docente123!"));
                docente.setRol(docenteRole);
                docente.setEstado(estadoRepository.findAll().get(0));
                docente.setEstrato(estratoRepository.findAll().get(0));
                docente.setFechaRegistro(LocalDate.now());
                docente.setFechaActualizacion(LocalDate.now());
                docente = usuarioRepository.save(docente);
            } else {
                docente = usuarioRepository.findByCorreoInstitucional("docente@uniremington.edu.co").get();
            }

            // Asignar estudiantes sin tutor al docente
            if (docente != null) {
                Role estudianteRole = roleRepository.findByNombreRol("Estudiante");

                // Crear estudiantes de prueba si no existen
                String[][] estudiantesSeed = {
                    {"Juan", "Pérez", "juan.perez@uniremington.edu.co", "Estudiante123!", "3"},
                    {"María", "Gómez", "maria.gomez@uniremington.edu.co", "Estudiante123!", "2"},
                    {"Carlos", "López", "carlos.lopez@uniremington.edu.co", "Estudiante123!", "4"},
                    {"Ana", "Martínez", "ana.martinez@uniremington.edu.co", "Estudiante123!", "1"},
                    {"Pedro", "Ramírez", "pedro.ramirez@uniremington.edu.co", "Estudiante123!", "5"},
                    {"Laura", "Torres", "laura.torres@uniremington.edu.co", "Estudiante123!", "3"},
                    {"Diego", "Herrera", "diego.herrera@uniremington.edu.co", "Estudiante123!", "2"},
                    {"Sofía", "Castro", "sofia.castro@uniremington.edu.co", "Estudiante123!", "4"},
                };

                for (String[] s : estudiantesSeed) {
                    if (usuarioRepository.findByCorreoInstitucional(s[2]).isEmpty()) {
                        Usuario est = new Usuario();
                        est.setNombres(s[0]);
                        est.setApellidos(s[1]);
                        est.setCorreoInstitucional(s[2]);
                        est.setContrasenaHash(passwordEncoder.encode(s[3]));
                        est.setRol(estudianteRole);
                        est.setEstado(estadoRepository.findAll().get(0));
                        est.setEstrato(estratoRepository.findById(Integer.parseInt(s[4])).orElse(null));
                        est.setDocenteTutor(docente);
                        est.setFechaRegistro(LocalDate.now());
                        est.setFechaActualizacion(LocalDate.now());
                        usuarioRepository.save(est);
                    }
                }

                for (Usuario u : usuarioRepository.findByRolId(estudianteRole.getId())) {
                    if (u.getDocenteTutor() == null) {
                        u.setDocenteTutor(docente);
                        u.setFechaActualizacion(LocalDate.now());
                        usuarioRepository.save(u);
                    }
                }
            }
        };
    }
}