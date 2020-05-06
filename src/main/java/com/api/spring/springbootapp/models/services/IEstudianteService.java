package com.api.spring.springbootapp.models.services;

import com.api.spring.springbootapp.models.entity.Estudiante;

import java.util.List;

public interface IEstudianteService{

    public List<Estudiante> findAll();

    public Estudiante findById(int id);

    public Estudiante save(Estudiante estudiante);

    public void delete(int id);

}
