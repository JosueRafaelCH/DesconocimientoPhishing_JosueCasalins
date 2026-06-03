package com.example.SimulatorApp.Service;

import com.example.SimulatorApp.DTO.LoginRequest;
import com.example.SimulatorApp.DTO.SignUpRequest;
import com.example.SimulatorApp.DTO.AuthResponse;
import com.example.SimulatorApp.Model.EstadoUsuario;
import com.example.SimulatorApp.Model.Rol;
import com.example.SimulatorApp.Model.Usuario;
import com.example.SimulatorApp.Repository.EstadoUsuarioRepository;
import com.example.SimulatorApp.Repository.RolRepository;
import com.example.SimulatorApp.Repository.UsuarioRepository;
import com.example.SimulatorApp.Security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private EstadoUsuarioRepository estadoUsuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthResponse login(LoginRequest loginRequest) {
        AuthResponse response = new AuthResponse();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getContraseña()
                    )
            );

            String token = jwtProvider.generateJwtToken(authentication);
            Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail()).get();

            response.setToken(token);
            response.setId(usuario.getIdUsuario());
            response.setNombre(usuario.getNombre());
            response.setEmail(usuario.getEmail());
            response.setRol(usuario.getRol().getNombreRol());
            response.setSuccess(true);
            response.setMessage("Login exitoso");

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error en el login: " + e.getMessage());
        }

        return response;
    }

    public AuthResponse signup(SignUpRequest signUpRequest) {
        AuthResponse response = new AuthResponse();

        try {
            // Validar que el usuario no exista
            if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
                response.setSuccess(false);
                response.setMessage("El email ya está registrado");
                return response;
            }

            // Crear nuevo usuario
            Usuario usuario = new Usuario();
            usuario.setNombre(signUpRequest.getNombre());
            usuario.setEmail(signUpRequest.getEmail());
            usuario.setContraseña(passwordEncoder.encode(signUpRequest.getContraseña()));

            // Asignar rol por defecto (ESTUDIANTE)
            Optional<Rol> rolEstudiante = rolRepository.findByNombreRol("ESTUDIANTE");
            if (rolEstudiante.isPresent()) {
                usuario.setRol(rolEstudiante.get());
            }

            // Asignar estado activo
            Optional<EstadoUsuario> estadoActivo = estadoUsuarioRepository.findByNombreEstado("Activo");
            if (estadoActivo.isPresent()) {
                usuario.setEstadoUsuario(estadoActivo.get());
            }

            Usuario usuarioGuardado = usuarioRepository.save(usuario);

            // Generar token
            String token = jwtProvider.generateTokenFromUsername(usuarioGuardado.getEmail());

            response.setToken(token);
            response.setId(usuarioGuardado.getIdUsuario());
            response.setNombre(usuarioGuardado.getNombre());
            response.setEmail(usuarioGuardado.getEmail());
            response.setRol(usuarioGuardado.getRol().getNombreRol());
            response.setSuccess(true);
            response.setMessage("Registro exitoso");

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error en el registro: " + e.getMessage());
        }

        return response;
    }
}
