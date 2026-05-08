package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimulatorApp.Model.Entity.EventoSimulacion;

@Repository
public interface EventoSimulacionDAOIface extends JpaRepository<EventoSimulacion, Integer> {
    
}
