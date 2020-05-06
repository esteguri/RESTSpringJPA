package com.api.spring.springbootapp.models.services;

import com.api.spring.springbootapp.models.entity.Programa;

import java.util.List;

public interface IProgramaService {

    public List<Programa> findAll();

    public Programa findById(int id);

    public Programa save(Programa programa);

    public void delete(int id);

}
