package com.example.SimulatorApp.Model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Respuestas_Test")
public class RespuestaTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test", nullable = false)
    private TestEvaluativo test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pregunta", nullable = false)
    private PreguntaTest pregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_opcion", nullable = false)
    private OpcionPregunta opcion;

    public RespuestaTest() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public TestEvaluativo getTest() { return test; }
    public void setTest(TestEvaluativo test) { this.test = test; }
    public PreguntaTest getPregunta() { return pregunta; }
    public void setPregunta(PreguntaTest pregunta) { this.pregunta = pregunta; }
    public OpcionPregunta getOpcion() { return opcion; }
    public void setOpcion(OpcionPregunta opcion) { this.opcion = opcion; }
}