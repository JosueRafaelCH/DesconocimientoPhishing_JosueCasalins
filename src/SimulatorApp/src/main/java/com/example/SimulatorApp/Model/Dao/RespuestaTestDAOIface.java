package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimulatorApp.Model.Entity.RespuestaTest;

@Repository
public interface RespuestaTestDAOIface extends JpaRepository<RespuestaTest, Integer> {
    
}
