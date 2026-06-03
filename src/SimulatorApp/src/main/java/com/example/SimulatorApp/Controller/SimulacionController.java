package com.example.SimulatorApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/simulacion")
public class SimulacionController {

    @GetMapping("/iniciar/{escenarioId}")
    public String iniciarSimulacion(@PathVariable Long escenarioId, Model model) {
        model.addAttribute("escenarioId", escenarioId);
        return "simulacion/iniciar";
    }

    @PostMapping("/guardar-respuesta")
    public String guardarRespuesta(
            @RequestParam Long escenarioId,
            @RequestParam String respuesta,
            Model model) {
        model.addAttribute("escenarioId", escenarioId);
        model.addAttribute("respuesta", respuesta);
        return "simulacion/resultado";
    }

    @GetMapping("/resultado/{simulacionId}")
    public String verResultado(@PathVariable Long simulacionId, Model model) {
        model.addAttribute("simulacionId", simulacionId);
        return "simulacion/resultado";
    }
}