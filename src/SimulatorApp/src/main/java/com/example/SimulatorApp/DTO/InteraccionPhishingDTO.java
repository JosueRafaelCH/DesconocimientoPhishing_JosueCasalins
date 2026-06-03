package com.example.SimulatorApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteraccionPhishingDTO {

    private Long idInteraccionPhishing;
    private Long idEscenarioPhishing;
    private String tipoInteraccion;
    private String respuestaUsuario;
    private Boolean esCorrecta;
    private Integer puntuacionObtenida;
}
