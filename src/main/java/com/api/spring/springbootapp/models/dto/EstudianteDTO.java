package com.api.spring.springbootapp.models.dto;

import com.api.spring.springbootapp.models.entity.Estudiante;
import com.api.spring.springbootapp.models.entity.Programa;

public class EstudianteDTO {

    private int id;
    private String cedula;
    private String nombre;
    private String correo;
    private int id_programa;

    public EstudianteDTO() {
    }

    public EstudianteDTO(int id, String cedula, String nombre, String correo, int id_programa) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.id_programa = id_programa;
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

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public Estudiante cambiarAEstudiante(){
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(this.nombre);
        estudiante.setCedula(this.cedula);
        estudiante.setCorreo(this.correo);
        estudiante.setPrograma(new Programa(this.id_programa));
        return estudiante;
    }
}
