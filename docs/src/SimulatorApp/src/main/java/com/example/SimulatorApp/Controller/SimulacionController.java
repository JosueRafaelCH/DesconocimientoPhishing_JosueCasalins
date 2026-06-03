package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Entity.*;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SimulacionController {

    private final SimiladorService simuladorService;

    public SimulacionController(SimiladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping("/simulacion")
    public String showSimulaciones(Authentication auth, Model model) {
        String correo = auth.getName();
        Usuario usuario = simuladorService.buscarUsuarioPorCorreo(correo);
        if (usuario == null) return "redirect:/login?error";

        List<EventoSimulacion> eventos = simuladorService.buscarEventosPorUsuario(usuario.getId());
        List<EscenarioPhishing> escenariosDisponibles = simuladorService.buscarEscenariosTodos();

        model.addAttribute("eventos", eventos);
        model.addAttribute("escenarios", escenariosDisponibles);
        return "bandeja_simulacion";
    }

    @GetMapping("/simulacion/{id}/interactuar")
    public String interactuar(@PathVariable Integer id, Model model) {
        EventoSimulacion evento = simuladorService.buscarEventoPorId(id);
        if (evento == null) return "redirect:/simulacion";

        InteraccionPhishing interaccion = simuladorService.buscarInteraccionPorEvento(id);
        List<FeedbackIA> feedbacks = null;
        if (interaccion != null) {
            feedbacks = simuladorService.buscarFeedbacksPorInteraccion(interaccion.getId());
        }

        model.addAttribute("evento", evento);
        model.addAttribute("interaccion", interaccion);
        model.addAttribute("feedbacks", feedbacks);
        return "simulacion_interactiva";
    }

    @PostMapping("/simulacion/{id}/clic")
    public String registrarClic(@PathVariable Integer id) {
        EventoSimulacion evento = simuladorService.buscarEventoPorId(id);
        if (evento == null) return "redirect:/simulacion";

        InteraccionPhishing interaccion = simuladorService.buscarInteraccionPorEvento(id);
        if (interaccion == null) {
            interaccion = new InteraccionPhishing();
            interaccion.setEvento(evento);
        }
        interaccion.setFechaClic(LocalDate.now());
        simuladorService.guardarInteraccion(interaccion);

        return "redirect:/simulacion/" + id + "/interactuar";
    }

    @PostMapping("/simulacion/{id}/datos")
    public String ingresarDatos(@PathVariable Integer id) {
        EventoSimulacion evento = simuladorService.buscarEventoPorId(id);
        if (evento == null) return "redirect:/simulacion";

        InteraccionPhishing interaccion = simuladorService.buscarInteraccionPorEvento(id);
        if (interaccion == null) {
            interaccion = new InteraccionPhishing();
            interaccion.setEvento(evento);
        }
        interaccion.setFechaDatosIngresados(LocalDate.now());
        simuladorService.guardarInteraccion(interaccion);

        FeedbackIA feedback = new FeedbackIA();
        feedback.setInteraccion(interaccion);
        feedback.setContenidoFeedback("Has interactuado con un escenario de phishing. Recuerda siempre verificar la URL y el remitente antes de proporcionar información personal.");
        feedback.setFechaGeneracion(LocalDate.now());
        feedback.setModeloIa("Sistema Interno v1.0");
        simuladorService.guardarFeedback(feedback);

        return "redirect:/simulacion/" + id + "/interactuar";
    }
}
