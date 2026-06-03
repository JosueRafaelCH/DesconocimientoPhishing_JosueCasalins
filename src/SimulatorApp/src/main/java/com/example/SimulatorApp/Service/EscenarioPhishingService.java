package com.example.SimulatorApp.Service;

import com.example.SimulatorApp.Model.EscenarioPhishing;
import com.example.SimulatorApp.Repository.EscenarioPhishingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscenarioPhishingService {

    @Autowired
    private EscenarioPhishingRepository escenarioRepository;

    public EscenarioPhishing guardar(EscenarioPhishing escenario) {
        return escenarioRepository.save(escenario);
    }

    public Optional<EscenarioPhishing> obtenerPorId(Long id) {
        return escenarioRepository.findById(id);
    }

    public List<EscenarioPhishing> obtenerTodos() {
        return escenarioRepository.findAll();
    }

    public List<EscenarioPhishing> obtenerPorNivel(Long nivelId) {
        return escenarioRepository.findByNivelDificultadId(nivelId);
    }

    public void eliminar(Long id) {
        escenarioRepository.deleteById(id);
    }
}