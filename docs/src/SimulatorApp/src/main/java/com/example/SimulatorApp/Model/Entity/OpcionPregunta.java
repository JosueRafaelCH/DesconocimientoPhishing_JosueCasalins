package com.example.SimulatorApp.Model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Opciones_Pregunta")
public class OpcionPregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opcion")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta", nullable = false)
    private PreguntaTest pregunta;

    @Column(name = "texto_opcion", nullable = false, length = 500)
    private String textoOpcion;

    @Column(name = "es_correcta", nullable = false)
    private Boolean esCorrecta;

    public OpcionPregunta() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public PreguntaTest getPregunta() { return pregunta; }
    public void setPregunta(PreguntaTest pregunta) { this.pregunta = pregunta; }
    public String getTextoOpcion() { return textoOpcion; }
    public void setTextoOpcion(String textoOpcion) { this.textoOpcion = textoOpcion; }
    public Boolean getEsCorrecta() { return esCorrecta; }
    public void setEsCorrecta(Boolean esCorrecta) { this.esCorrecta = esCorrecta; }
}