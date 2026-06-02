package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Service.SimiladorService;
import com.example.SimulatorApp.Model.Entity.Usuario;
import com.example.SimulatorApp.Model.Dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final SimiladorService simuladorService;

    public AuthController(SimiladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerRequest") RegisterRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        Usuario usuario = new Usuario();
        usuario.setNombres(request.getNombres());
        usuario.setApellidos(request.getApellidos());
        usuario.setCorreoInstitucional(request.getCorreo());
        usuario.setContrasenaHash(request.getContrasena());
        
        simuladorService.register(usuario);
        return "redirect:/login";
    }
}