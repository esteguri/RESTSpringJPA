package com.api.spring.springbootapp.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "programa")
public class Programa implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;

    public Programa() {
    }

    public Programa(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
