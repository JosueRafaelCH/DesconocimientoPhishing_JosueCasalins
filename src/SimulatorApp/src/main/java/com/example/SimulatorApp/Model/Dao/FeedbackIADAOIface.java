package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimulatorApp.Model.Entity.FeedbackIA;

@Repository
public interface FeedbackIADAOIface extends JpaRepository<FeedbackIA, Integer> {
    
}
