package com.example.SimulatorApp.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());

        String role = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst().orElse("");

        return switch (role) {
            case "ROLE_ADMIN" -> "redirect:/admin";
            case "ROLE_DOCENTE" -> "redirect:/docente/panel";
            default -> "dashboard_estudiante";
        };
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }
}
