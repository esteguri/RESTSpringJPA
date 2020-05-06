package com.api.spring.springbootapp.models.services;

import com.api.spring.springbootapp.models.dao.IEstudianteDAO;
import com.api.spring.springbootapp.models.entity.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    @Autowired
    private IEstudianteDAO iEstudianteDAO;

    @Override
    public List<Estudiante> findAll() {
        return (List<Estudiante>) iEstudianteDAO.findAll();
    }

    @Override
    public Estudiante findById(int id) {
        return iEstudianteDAO.findById(id).orElse(null);
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return iEstudianteDAO.save(estudiante);
    }

    @Override
    public void delete(int id) {
        iEstudianteDAO.deleteById(id);
    }
}
