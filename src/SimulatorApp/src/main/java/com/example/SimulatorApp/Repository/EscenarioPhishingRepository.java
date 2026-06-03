package com.example.SimulatorApp.Repository;

import com.example.SimulatorApp.Model.EscenarioPhishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscenarioPhishingRepository extends JpaRepository<EscenarioPhishing, Long> {
    List<EscenarioPhishing> findByNivelDificultadId(Long nivelId);
}