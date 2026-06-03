package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.EscenarioPhishing;
import com.example.SimulatorApp.Service.EscenarioPhishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/escenarios")
public class EscenarioPhishingController {

    @Autowired
    private EscenarioPhishingService escenarioService;

    @GetMapping
    public String listar(Model model) {
        List<EscenarioPhishing> escenarios = escenarioService.obtenerTodos();
        model.addAttribute("escenarios", escenarios);
        return "escenarios/lista";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model) {
        EscenarioPhishing escenario = escenarioService.obtenerPorId(id);
        if (escenario != null) {
            model.addAttribute("escenario", escenario);
            return "escenarios/detalle";
        }
        return "redirect:/escenarios";
    }

    @GetMapping("/dificultad/{nivelId}")
    public String porDificultad(@PathVariable Long nivelId, Model model) {
        List<EscenarioPhishing> escenarios = escenarioService.obtenerPorNivel(nivelId);
        model.addAttribute("escenarios", escenarios);
        return "escenarios/lista";
    }
}