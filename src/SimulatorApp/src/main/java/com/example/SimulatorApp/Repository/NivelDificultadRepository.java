package com.example.SimulatorApp.Repository;

import com.example.SimulatorApp.Model.NivelDificultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NivelDificultadRepository extends JpaRepository<NivelDificultad, Long> {
    Optional<NivelDificultad> findByNombreNivel(String nombreNivel);
}
