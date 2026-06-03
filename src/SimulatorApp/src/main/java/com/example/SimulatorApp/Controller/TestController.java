package com.example.SimulatorApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tests")
public class TestController {

    @GetMapping
    public String listarTests(Model model) {
        return "tests/lista";
    }

    @GetMapping("/realizar/{testId}")
    public String realizarTest(@PathVariable Long testId, Model model) {
        model.addAttribute("testId", testId);
        return "tests/realizar";
    }

    @PostMapping("/enviar/{testId}")
    public String enviarRespuestas(
            @PathVariable Long testId,
            @RequestParam(required = false) String respuestas,
            Model model) {
        model.addAttribute("testId", testId);
        return "tests/resultado";
    }

    @GetMapping("/resultados")
    public String verResultados(Model model) {
        return "tests/resultados";
    }
}