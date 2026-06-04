package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Entity.*;
import com.example.SimulatorApp.Model.Service.SimiladorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * DashboardController
 * Redireccion por roles:
 *   ROLE_ADMIN  -> /admin
 *   ROLE_DOCENTE -> /docente/panel
 *   ROLE_ESTUDIANTE -> /estudiante/dashboard
 * RF02 - Autenticacion segura (redireccion post-login basada en rol)
 * CU-01B - Autenticar Usuario (flujo de redireccion a dashboard segun rol)
 * RNF03 - Usabilidad: dashboard con cards de acceso rapido
 */
@Controller
public class DashboardController {

    private final SimiladorService simuladorService;

    public DashboardController(SimiladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model, HttpSession session) {
        String correo = authentication.getName();
        Usuario usuario = simuladorService.buscarUsuarioPorCorreo(correo);
        if (usuario == null) return "redirect:/login?error";

        session.setAttribute("usuario", usuario);
        model.addAttribute("username", correo);

        String role = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst().orElse("");

        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        if (role.equals("ROLE_DOCENTE")) {
            return "redirect:/docente/panel";
        }

        List<TestEvaluativo> tests = simuladorService.buscarTestsPorUsuario(usuario.getId());
        int testCount = tests.size();
        double avgScore = tests.stream()
            .filter(t -> t.getCalificacion() != null)
            .mapToDouble(TestEvaluativo::getCalificacion)
            .average().orElse(0.0);
        List<EventoSimulacion> eventos = simuladorService.buscarEventosPorUsuario(usuario.getId());

        model.addAttribute("testCount", testCount);
        model.addAttribute("avgScore", avgScore);
        model.addAttribute("eventoCount", eventos.size());
        return "estudiante/dashboard";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }
}
