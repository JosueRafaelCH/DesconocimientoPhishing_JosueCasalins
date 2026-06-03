package com.example.SimulatorApp.Config;

import com.example.SimulatorApp.Model.Dao.*;
import com.example.SimulatorApp.Model.Entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(UsuarioDAOIface usuarioRepository, RoleDAOIface roleRepository, 
                                   EstadoUsuarioDAOIface estadoRepository, EstratoDAOIface estratoRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role(null, "Admin", null));
                roleRepository.save(new Role(null, "Docente", null));
                roleRepository.save(new Role(null, "Estudiante", null));
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

            if (usuarioRepository.findByCorreoInstitucional("admin@itm.edu.co").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNombres("Admin");
                admin.setApellidos("Sistema");
                admin.setCorreoInstitucional("admin@itm.edu.co");
                admin.setContrasenaHash(passwordEncoder.encode("Admin123!"));
                admin.setRol(adminRole);
                admin.setEstado(estadoRepository.findAll().get(0));
                admin.setEstrato(estratoRepository.findAll().get(0));
                admin.setFechaRegistro(LocalDate.now());
                admin.setFechaActualizacion(LocalDate.now());
                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByCorreoInstitucional("admin2@itm.edu.co").isEmpty()) {
                Usuario admin2 = new Usuario();
                admin2.setNombres("Admin2");
                admin2.setApellidos("Seguridad");
                admin2.setCorreoInstitucional("admin2@itm.edu.co");
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
            if (usuarioRepository.findByCorreoInstitucional("docente@itm.edu.co").isEmpty()) {
                docente = new Usuario();
                docente.setNombres("Carlos");
                docente.setApellidos("Mendoza");
                docente.setCorreoInstitucional("docente@itm.edu.co");
                docente.setContrasenaHash(passwordEncoder.encode("Docente123!"));
                docente.setRol(docenteRole);
                docente.setEstado(estadoRepository.findAll().get(0));
                docente.setEstrato(estratoRepository.findAll().get(0));
                docente.setFechaRegistro(LocalDate.now());
                docente.setFechaActualizacion(LocalDate.now());
                docente = usuarioRepository.save(docente);
            } else {
                docente = usuarioRepository.findByCorreoInstitucional("docente@itm.edu.co").get();
            }

            // Asignar estudiantes sin tutor al docente
            if (docente != null) {
                Role estudianteRole = roleRepository.findByNombreRol("Estudiante");
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