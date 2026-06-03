package com.example.SimulatorApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Estratos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Estrato")
    private Long idEstrato;

    @Column(name = "Nombre_Estrato", unique = true, nullable = false)
    private String nombreEstrato;

    @Column(name = "Descripcion")
    private String descripcion;
}
