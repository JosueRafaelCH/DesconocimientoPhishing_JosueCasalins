package com.example.SimulatorApp.Repository;

import com.example.SimulatorApp.Model.Estrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstratoRepository extends JpaRepository<Estrato, Long> {
}
