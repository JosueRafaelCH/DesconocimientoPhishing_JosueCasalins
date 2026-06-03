package com.example.SimulatorApp.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("usuario", authentication.getName());
        }
        return "dashboard";
    }

    @GetMapping("/estudiante")
    public String dashboardEstudiante(Model model) {
        return "dashboard-estudiante";
    }

    @GetMapping("/profesor")
    public String dashboardProfesor(Model model) {
        return "dashboard-profesor";
    }

    @GetMapping("/admin")
    public String dashboardAdmin(Model model) {
        return "dashboard-admin";
    }
}