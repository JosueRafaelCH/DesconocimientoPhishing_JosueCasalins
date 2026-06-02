package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Dao.EventoSimulacionDAOIface;
import com.example.SimulatorApp.Model.Entity.EventoSimulacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SimulacionController {

    @Autowired
    private EventoSimulacionDAOIface eventoRepository;

    @GetMapping("/simulacion")
    public String showSimulaciones(Model model) {
        List<EventoSimulacion> eventos = eventoRepository.findAll();
        model.addAttribute("eventos", eventos);
        return "bandeja_simulacion";
    }
}