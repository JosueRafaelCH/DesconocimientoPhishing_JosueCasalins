package com.example.SimulatorApp.Model.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Estratos")
public class Estrato {
    @Id
    @Column(name = "id_estrato")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String descripcion;

    @OneToMany(mappedBy = "estrato", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Usuario> usuarios = new ArrayList<>();

    public Estrato() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}