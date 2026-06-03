package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimulatorApp.Model.Entity.EventoSimulacion;

import java.util.List;

@Repository
public interface EventoSimulacionDAOIface extends JpaRepository<EventoSimulacion, Integer> {

    List<EventoSimulacion> findByUsuarioId(Integer idUsuario);

    List<EventoSimulacion> findByEscenarioId(Integer idEscenario);

    List<EventoSimulacion> findByEstadoEventoId(Integer idEstadoEvento);

    List<EventoSimulacion> findByTestId(Integer idTest);
}
