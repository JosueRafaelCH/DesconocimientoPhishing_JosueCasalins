package com.example.SimulatorApp.Service;

import com.example.SimulatorApp.Model.Usuario;
import com.example.SimulatorApp.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (usuarioExistente.isPresent()) {
            throw new Exception("El correo ya está registrado");
        }
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public boolean validarCredenciales(String correo, String contraseña) {
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);
        if (usuario.isPresent()) {
            return passwordEncoder.matches(contraseña, usuario.get().getContraseña());
        }
        return false;
    }
}