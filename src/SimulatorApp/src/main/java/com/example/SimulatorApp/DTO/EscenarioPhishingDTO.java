package com.example.SimulatorApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscenarioPhishingDTO {

    private Long idEscenarioPhishing;
    private String titulo;
    private String descripcion;
    private String nombreNivel;
    private String contenidoEscenario;
}
