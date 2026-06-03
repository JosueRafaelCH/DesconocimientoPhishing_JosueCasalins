package com.example.SimulatorApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long idUsuario;
    private String nombre;
    private String email;
    private String nombreRol;
    private String nombreEstado;
    private String nombreEstrato;
}
