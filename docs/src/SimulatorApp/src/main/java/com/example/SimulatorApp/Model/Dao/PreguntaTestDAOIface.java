package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimulatorApp.Model.Entity.PreguntaTest;

import java.util.List;

@Repository
public interface PreguntaTestDAOIface extends JpaRepository<PreguntaTest, Integer> {

    @EntityGraph(attributePaths = {"opciones"})
    List<PreguntaTest> findAll();

    @EntityGraph(attributePaths = {"opciones"})
    List<PreguntaTest> findByNivelId(Integer idNivel);
}
