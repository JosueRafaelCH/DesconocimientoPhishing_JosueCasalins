package com.example.SimulatorApp.Service;

import com.example.SimulatorApp.Model.InteraccionPhishing;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InteraccionPhishingService {

    public InteraccionPhishing registrarInteraccion(Long usuarioId, Long escenarioId, String tipo) {
        InteraccionPhishing interaccion = new InteraccionPhishing();
        interaccion.setFechaHora(LocalDateTime.now());
        interaccion.setTipo(tipo);
        return interaccion;
    }
}