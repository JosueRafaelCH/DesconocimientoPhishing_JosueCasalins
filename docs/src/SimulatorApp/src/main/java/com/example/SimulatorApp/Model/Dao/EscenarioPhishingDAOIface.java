package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimulatorApp.Model.Entity.EscenarioPhishing;

import java.util.List;

@Repository
public interface EscenarioPhishingDAOIface extends JpaRepository<EscenarioPhishing, Integer> {

    List<EscenarioPhishing> findByNivelId(Integer idNivel);
}