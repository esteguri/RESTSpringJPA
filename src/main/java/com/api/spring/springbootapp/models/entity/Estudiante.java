package com.api.spring.springbootapp.models.entity;

import com.api.spring.springbootapp.models.dto.EstudianteDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estudiante")
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String cedula;
    private String nombre;
    private String correo;

    @ManyToOne()
    @JoinColumn(name = "id_programa")
    private Programa programa;

    public Estudiante() {
    }

    public Estudiante(String cedula, String nombre, String correo, Programa programa) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.programa = programa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public EstudianteDTO cambiarAEstudianteDTO(){
        EstudianteDTO estudianteDTO = new EstudianteDTO();
        estudianteDTO.setId(this.id);
        estudianteDTO.setNombre(this.nombre);
        estudianteDTO.setCedula(this.cedula);
        estudianteDTO.setCorreo(this.correo);
        estudianteDTO.setId_programa(this.programa.getId());
        return estudianteDTO;
    }
}
