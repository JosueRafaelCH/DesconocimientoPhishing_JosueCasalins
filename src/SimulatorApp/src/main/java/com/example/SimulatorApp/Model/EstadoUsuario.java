package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Estados_Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Estado_Usuario")
    private Long idEstadoUsuario;

    @Column(name = "Nombre_Estado", unique = true, nullable = false)
    private String nombreEstado;

    @Column(name = "Descripcion")
    private String descripcion;
}
