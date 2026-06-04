package com.example.SimulatorApp.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Respuestas_Test")
public class RespuestaTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test", nullable = false)
    @JsonManagedReference
    private TestEvaluativo test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta", nullable = false)
    @JsonManagedReference
    private PreguntaTest pregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_opcion", nullable = false)
    @JsonManagedReference
    private OpcionPregunta opcion;

    @Column(name = "es_correcta")
    private Boolean esCorrecta;

    public RespuestaTest() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public TestEvaluativo getTest() { return test; }
    public void setTest(TestEvaluativo test) { this.test = test; }
    public PreguntaTest getPregunta() { return pregunta; }
    public void setPregunta(PreguntaTest pregunta) { this.pregunta = pregunta; }
    public OpcionPregunta getOpcion() { return opcion; }
    public void setOpcion(OpcionPregunta opcion) { this.opcion = opcion; }
    public Boolean getEsCorrecta() { return esCorrecta; }
    public void setEsCorrecta(Boolean esCorrecta) { this.esCorrecta = esCorrecta; }
}
