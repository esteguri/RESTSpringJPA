package com.api.spring.springbootapp.models.dao;

import com.api.spring.springbootapp.models.entity.Programa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProgramaDAO extends CrudRepository<Programa, Integer> {
}
