package com.example.SimulatorApp.Model.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimulatorApp.Model.Entity.Role;

@Repository
public interface RoleDAOIface extends JpaRepository<Role, Integer> {
    
}
