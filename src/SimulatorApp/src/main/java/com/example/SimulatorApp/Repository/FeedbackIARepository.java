package com.example.SimulatorApp.Repository;

import com.example.SimulatorApp.Model.FeedbackIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackIARepository extends JpaRepository<FeedbackIA, Long> {
}
