package com.example.SimulatorApp.Repository;

import com.example.SimulatorApp.Model.EventoSimulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoSimulacionRepository extends JpaRepository<EventoSimulacion, Long> {
    List<EventoSimulacion> findByUsuario_IdUsuario(Long idUsuario);
}
