package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimulatorApp.Model.Entity.InteraccionPhishing;

import java.util.Optional;

@Repository
public interface InteraccionPhishingDAOIface extends JpaRepository<InteraccionPhishing, Integer> {

    Optional<InteraccionPhishing> findByEventoId(Integer idEvento);
}
