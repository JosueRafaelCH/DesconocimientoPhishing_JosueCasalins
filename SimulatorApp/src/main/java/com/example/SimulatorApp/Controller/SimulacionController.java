package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Entity.*;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

/**
 * SimulacionController
 * RF04 - Ejecucion de simulaciones de phishing
 * RF05 - Retroalimentacion inmediata tras cada interaccion
 * RF07 - Registro de desempeno (fecha, tipo, resultado)
 * CU-05 - Procesar Interaccion de Simulacion
 * RNF02 - Datos ficticios sin informacion real del usuario
 * RNF03 - Interfaz clara con mensajes educativos
 * RNF04 - Tiempo de carga < 3s (sin llamadas externas)
 */
@Controller
public class SimulacionController {

    private final SimiladorService simuladorService;

    public SimulacionController(SimiladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping("/simulaciones")
    public String showSimulaciones(Authentication auth, Model model) {
        String correo = auth.getName();
        Usuario usuario = simuladorService.buscarUsuarioPorCorreo(correo);
        if (usuario == null) return "redirect:/login?error";

        List<EventoSimulacion> eventos = simuladorService.buscarEventosPorUsuario(usuario.getId());
        model.addAttribute("eventos", eventos);
        return "estudiante/simulaciones";
    }

    @GetMapping("/simulaciones/interactuar/{id}")
    public String interactuar(@PathVariable Integer id, Model model) {
        EventoSimulacion evento = simuladorService.buscarEventoPorId(id);
        if (evento == null) return "redirect:/simulaciones";

        InteraccionPhishing interaccion = simuladorService.buscarInteraccionPorEvento(id);
        List<FeedbackIA> feedbacks = null;
        if (interaccion != null) {
            feedbacks = simuladorService.buscarFeedbacksPorInteraccion(interaccion.getId());
        }

        model.addAttribute("evento", evento);
        model.addAttribute("interaccion", interaccion);
        model.addAttribute("feedbacks", feedbacks);
        return "estudiante/simulacion_interactiva";
    }

    @PostMapping("/simulaciones/clic/{id}")
    public String registrarClic(@PathVariable Integer id) {
        EventoSimulacion evento = simuladorService.buscarEventoPorId(id);
        if (evento == null) return "redirect:/simulaciones";

        InteraccionPhishing interaccion = simuladorService.buscarInteraccionPorEvento(id);
        if (interaccion == null) {
            interaccion = new InteraccionPhishing();
            interaccion.setEvento(evento);
        }
        interaccion.setFechaClic(LocalDate.now());
        simuladorService.guardarInteraccion(interaccion);

        FeedbackIA feedback = new FeedbackIA();
        feedback.setInteraccion(interaccion);
        feedback.setContenidoFeedback("Hiciste clic en un enlace sospechoso. Recuerda: antes de hacer clic, verifica la URL completa y asegúrate de que pertenece al dominio oficial.");
        feedback.setFechaGeneracion(LocalDate.now());
        feedback.setModeloIa("Sistema Interno v1.0");
        simuladorService.guardarFeedback(feedback);

        return "redirect:/simulaciones/interactuar/" + id;
    }

    @PostMapping("/simulaciones/datos/{id}")
    public String ingresarDatos(@PathVariable Integer id) {
        EventoSimulacion evento = simuladorService.buscarEventoPorId(id);
        if (evento == null) return "redirect:/simulaciones";

        InteraccionPhishing interaccion = simuladorService.buscarInteraccionPorEvento(id);
        if (interaccion == null) {
            interaccion = new InteraccionPhishing();
            interaccion.setEvento(evento);
        }
        interaccion.setFechaDatosIngresados(LocalDate.now());
        simuladorService.guardarInteraccion(interaccion);

        FeedbackIA feedback = new FeedbackIA();
        feedback.setInteraccion(interaccion);
        feedback.setContenidoFeedback("Ingresaste datos personales en un formulario. Nunca compartas información sensible como contraseñas, números de documento o datos bancarios en sitios no verificados.");
        feedback.setFechaGeneracion(LocalDate.now());
        feedback.setModeloIa("Sistema Interno v1.0");
        simuladorService.guardarFeedback(feedback);

        return "redirect:/simulaciones/interactuar/" + id;
    }
}
