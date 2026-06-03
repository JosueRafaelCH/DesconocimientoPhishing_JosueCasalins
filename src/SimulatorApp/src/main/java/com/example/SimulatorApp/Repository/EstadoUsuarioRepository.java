package com.example.SimulatorApp.Repository;

import com.example.SimulatorApp.Model.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoUsuarioRepository extends JpaRepository<EstadoUsuario, Long> {
    Optional<EstadoUsuario> findByNombreEstado(String nombreEstado);
}
