package com.api.spring.springbootapp.models.dao;

import com.api.spring.springbootapp.models.entity.Estudiante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudianteDAO extends CrudRepository<Estudiante,Integer> {
}
