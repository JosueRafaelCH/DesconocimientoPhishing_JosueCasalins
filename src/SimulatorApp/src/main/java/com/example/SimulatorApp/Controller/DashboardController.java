package com.example.SimulatorApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        // Here we will add logic to check user role and return appropriate dashboard
        return "dashboard_estudiante";
    }
}