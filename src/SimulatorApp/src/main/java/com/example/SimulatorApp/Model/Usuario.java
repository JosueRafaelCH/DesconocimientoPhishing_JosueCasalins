package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Long idUsuario;

    @Column(name = "Nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Contraseña", nullable = false)
    private String contraseña;

    @ManyToOne
    @JoinColumn(name = "ID_Rol", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "ID_Estado_Usuario")
    private EstadoUsuario estadoUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_Estrato")
    private Estrato estrato;

    @Column(name = "Fecha_Creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "Fecha_Ultima_Actualizacion")
    private LocalDateTime fecheUltimaActualizacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fecheUltimaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fecheUltimaActualizacion = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.getNombreRol()));
    }

    @Override
    public String getPassword() {
        return contraseña;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return estadoUsuario != null && "Activo".equals(estadoUsuario.getNombreEstado());
    }
}
