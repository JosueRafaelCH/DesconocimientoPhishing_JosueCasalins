package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.DTO.InteraccionPhishingDTO;
import com.example.SimulatorApp.Model.InteraccionPhishing;
import com.example.SimulatorApp.Service.InteraccionPhishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interacciones")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InteraccionPhishingController {

    @Autowired
    private InteraccionPhishingService interaccionPhishingService;

    @PostMapping
    public ResponseEntity<InteraccionPhishing> createInteraccion(@RequestBody InteraccionPhishing interaccion) {
        InteraccionPhishing saved = interaccionPhishingService.saveInteraccion(interaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<InteraccionPhishing>> getInteraccionesByUsuario(@PathVariable Long idUsuario) {
        List<InteraccionPhishing> interacciones = interaccionPhishingService.getInteraccionesByUsuario(idUsuario);
        return ResponseEntity.ok(interacciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteraccionPhishing> getInteraccionById(@PathVariable Long id) {
        Optional<InteraccionPhishing> interaccion = interaccionPhishingService.getInteraccionById(id);
        if (interaccion.isPresent()) {
            return ResponseEntity.ok(interaccion.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{idUsuario}/correctas")
    public ResponseEntity<Integer> getCorrectasCount(@PathVariable Long idUsuario) {
        Integer count = interaccionPhishingService.getCorrectasCount(idUsuario);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/usuario/{idUsuario}/incorrectas")
    public ResponseEntity<Integer> getIncorrectasCount(@PathVariable Long idUsuario) {
        Integer count = interaccionPhishingService.getIncorrectasCount(idUsuario);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/usuario/{idUsuario}/promedio")
    public ResponseEntity<Double> getPromedioDesempenio(@PathVariable Long idUsuario) {
        double promedio = interaccionPhishingService.getPromedioDesempenio(idUsuario);
        return ResponseEntity.ok(promedio);
    }
}
