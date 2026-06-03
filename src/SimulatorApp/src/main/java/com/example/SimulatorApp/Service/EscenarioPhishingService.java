package com.example.SimulatorApp.Service;

import com.example.SimulatorApp.DTO.EscenarioPhishingDTO;
import com.example.SimulatorApp.Model.EscenarioPhishing;
import com.example.SimulatorApp.Repository.EscenarioPhishingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EscenarioPhishingService {

    @Autowired
    private EscenarioPhishingRepository escenarioPhishingRepository;

    public List<EscenarioPhishing> getAllEscenarios() {
        return escenarioPhishingRepository.findAll();
    }

    public Optional<EscenarioPhishing> getEscenarioById(Long id) {
        return escenarioPhishingRepository.findById(id);
    }

    public List<EscenarioPhishing> getEscenariosByNivel(Long idNivel) {
        return escenarioPhishingRepository.findByNivelDificultad_IdNivelDificultad(idNivel);
    }

    public EscenarioPhishing saveEscenario(EscenarioPhishing escenario) {
        return escenarioPhishingRepository.save(escenario);
    }

    public void deleteEscenario(Long id) {
        escenarioPhishingRepository.deleteById(id);
    }

    public EscenarioPhishingDTO convertToDTO(EscenarioPhishing escenario) {
        EscenarioPhishingDTO dto = new EscenarioPhishingDTO();
        dto.setIdEscenarioPhishing(escenario.getIdEscenarioPhishing());
        dto.setTitulo(escenario.getTitulo());
        dto.setDescripcion(escenario.getDescripcion());
        if (escenario.getNivelDificultad() != null) {
            dto.setNombreNivel(escenario.getNivelDificultad().getNombreNivel());
        }
        dto.setContenidoEscenario(escenario.getContenidoEscenario());
        return dto;
    }
}
