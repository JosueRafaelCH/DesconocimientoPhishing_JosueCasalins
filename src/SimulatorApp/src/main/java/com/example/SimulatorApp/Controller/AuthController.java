package com.example.SimulatorApp.Controller;

import com.example.SimulatorApp.Model.Usuario;
import com.example.SimulatorApp.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registro")
    public String registro(
            @RequestParam String correo,
            @RequestParam String contraseña,
            @RequestParam String nombre,
            RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = new Usuario();
            usuario.setCorreo(correo);
            usuario.setContraseña(contraseña);
            usuario.setNombre(nombre);
            authService.registrarUsuario(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "¡Registro exitoso! Inicia sesión con tus credenciales.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error en el registro: " + e.getMessage());
            return "redirect:/registro";
        }
    }
}