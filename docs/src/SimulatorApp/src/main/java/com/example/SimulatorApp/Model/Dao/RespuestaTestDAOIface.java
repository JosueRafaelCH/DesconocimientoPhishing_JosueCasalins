package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimulatorApp.Model.Entity.RespuestaTest;

import java.util.List;

@Repository
public interface RespuestaTestDAOIface extends JpaRepository<RespuestaTest, Integer> {

    List<RespuestaTest> findByTestId(Integer idTest);
}
