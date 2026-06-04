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

/**
 * AuthController
 * RF01 - Registro con correo institucional
 * RF02 - Autenticacion segura
 * CU-01A - Registrar Usuario
 * CU-01B - Autenticar Usuario
 * RNF01 - Almacenamiento cifrado con BCrypt (delegado a SimiladorService.register)
 * RNF03 - Interfaz intuitiva con mensajes de error
 */
@Controller
public class AuthController {

    private final SimiladorService simuladorService;

    public AuthController(SimiladorService simuladorService) {
        this.simuladorService = simuladorService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        model.addAttribute("estratos", simuladorService.buscarEstratosTodos());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerRequest") RegisterRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("estratos", simuladorService.buscarEstratosTodos());
            return "auth/register";
        }
        
        Usuario usuario = new Usuario();
        usuario.setNombres(request.getNombres());
        usuario.setApellidos(request.getApellidos());
        usuario.setCorreoInstitucional(request.getCorreo());
        usuario.setContrasenaHash(request.getContrasena());
        
        usuario.setRol(simuladorService.buscarRolePorId(3)); // Estudiante fijo
        usuario.setEstrato(simuladorService.buscarEstratoPorId(request.getIdEstrato()));
        
        simuladorService.register(usuario);
        return "redirect:/login";
    }
}
