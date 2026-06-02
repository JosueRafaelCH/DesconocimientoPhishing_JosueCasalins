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

            if (usuarioRepository.findByCorreoInstitucional("admin@itm.edu.co").isEmpty()) {
                Role adminRole = roleRepository.findByNombreRol("Admin");
                
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
        };
    }
}