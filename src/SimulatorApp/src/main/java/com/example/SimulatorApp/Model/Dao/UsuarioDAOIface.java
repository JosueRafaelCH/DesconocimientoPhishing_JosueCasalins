package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SimulatorApp.Model.Entity.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioDAOIface extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoInstitucional(String correoInstitucional);
}
