package com.example.SimulatorApp.Service;

import com.example.SimulatorApp.DTO.UsuarioDTO;
import com.example.SimulatorApp.Model.Usuario;
import com.example.SimulatorApp.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        if (usuario.getRol() != null) {
            dto.setNombreRol(usuario.getRol().getNombreRol());
        }
        if (usuario.getEstadoUsuario() != null) {
            dto.setNombreEstado(usuario.getEstadoUsuario().getNombreEstado());
        }
        if (usuario.getEstrato() != null) {
            dto.setNombreEstrato(usuario.getEstrato().getNombreEstrato());
        }
        return dto;
    }
}
