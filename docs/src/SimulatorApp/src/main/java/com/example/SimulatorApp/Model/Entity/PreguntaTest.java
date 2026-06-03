package com.example.SimulatorApp.Model.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Preguntas_Test")
public class PreguntaTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String enunciado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel", nullable = false)
    private NivelDificultad nivel;

    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL)
    private List<OpcionPregunta> opciones;

    public PreguntaTest() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    public List<OpcionPregunta> getOpciones() { return opciones; }
    public void setOpciones(List<OpcionPregunta> opciones) { this.opciones = opciones; }
}