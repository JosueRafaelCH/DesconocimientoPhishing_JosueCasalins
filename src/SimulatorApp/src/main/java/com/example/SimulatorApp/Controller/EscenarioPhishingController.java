package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.DTO.EscenarioPhishingDTO;
import com.example.SimulatorApp.Model.EscenarioPhishing;
import com.example.SimulatorApp.Service.EscenarioPhishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/scenarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EscenarioPhishingController {

    @Autowired
    private EscenarioPhishingService escenarioPhishingService;

    @GetMapping
    public ResponseEntity<List<EscenarioPhishingDTO>> getAllEscenarios() {
        List<EscenarioPhishing> escenarios = escenarioPhishingService.getAllEscenarios();
        List<EscenarioPhishingDTO> dtos = escenarios.stream()
                .map(escenarioPhishingService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscenarioPhishingDTO> getEscenarioById(@PathVariable Long id) {
        Optional<EscenarioPhishing> escenario = escenarioPhishingService.getEscenarioById(id);
        if (escenario.isPresent()) {
            return ResponseEntity.ok(escenarioPhishingService.convertToDTO(escenario.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nivel/{idNivel}")
    public ResponseEntity<List<EscenarioPhishingDTO>> getEscenariosByNivel(@PathVariable Long idNivel) {
        List<EscenarioPhishing> escenarios = escenarioPhishingService.getEscenariosByNivel(idNivel);
        List<EscenarioPhishingDTO> dtos = escenarios.stream()
                .map(escenarioPhishingService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EscenarioPhishingDTO> createEscenario(@RequestBody EscenarioPhishing escenario) {
        EscenarioPhishing saved = escenarioPhishingService.saveEscenario(escenario);
        return ResponseEntity.status(HttpStatus.CREATED).body(escenarioPhishingService.convertToDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscenarioPhishingDTO> updateEscenario(
            @PathVariable Long id,
            @RequestBody EscenarioPhishing escenarioDetails) {
        Optional<EscenarioPhishing> escenario = escenarioPhishingService.getEscenarioById(id);
        if (escenario.isPresent()) {
            EscenarioPhishing toUpdate = escenario.get();
            toUpdate.setTitulo(escenarioDetails.getTitulo());
            toUpdate.setDescripcion(escenarioDetails.getDescripcion());
            toUpdate.setContenidoEscenario(escenarioDetails.getContenidoEscenario());
            toUpdate.setRespuestaCorrecta(escenarioDetails.getRespuestaCorrecta());
            EscenarioPhishing updated = escenarioPhishingService.saveEscenario(toUpdate);
            return ResponseEntity.ok(escenarioPhishingService.convertToDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEscenario(@PathVariable Long id) {
        if (escenarioPhishingService.getEscenarioById(id).isPresent()) {
            escenarioPhishingService.deleteEscenario(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
