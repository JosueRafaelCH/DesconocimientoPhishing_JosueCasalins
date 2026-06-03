package com.example.SimulatorApp.Model.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Estados_Usuario")
public class EstadoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer id;

    @Column(name = "nombre_estado", nullable = false, length = 50)
    private String nombreEstado;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Usuario> usuarios = new ArrayList<>();

    public EstadoUsuario() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombreEstado() { return nombreEstado; }
    public void setNombreEstado(String nombreEstado) { this.nombreEstado = nombreEstado; }
}