package com.example.SimulatorApp.Repository;

import com.example.SimulatorApp.Model.TestEvaluativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEvaluativoRepository extends JpaRepository<TestEvaluativo, Long> {
}
