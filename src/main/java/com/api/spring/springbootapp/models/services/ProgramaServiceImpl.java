package com.api.spring.springbootapp.models.services;

import com.api.spring.springbootapp.models.dao.IProgramaDAO;
import com.api.spring.springbootapp.models.entity.Programa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramaServiceImpl implements IProgramaService {

    @Autowired
    private IProgramaDAO iProgramaDAO;

    @Override
    public List<Programa> findAll() {
        return (List<Programa>) iProgramaDAO.findAll();
    }

    @Override
    public Programa findById(int id) {
        return iProgramaDAO.findById(id).orElse(null);
    }

    @Override
    public Programa save(Programa programa) {
        return iProgramaDAO.save(programa);
    }

    @Override
    public void delete(int id) {
        iProgramaDAO.deleteById(id);
    }
}
