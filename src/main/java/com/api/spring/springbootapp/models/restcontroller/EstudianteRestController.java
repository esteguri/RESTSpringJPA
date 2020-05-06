package com.api.spring.springbootapp.models.restcontroller;

import com.api.spring.springbootapp.models.dto.EstudianteDTO;
import com.api.spring.springbootapp.models.entity.Estudiante;
import com.api.spring.springbootapp.models.services.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/estudiantes")
public class EstudianteRestController {

    @Autowired
    private IEstudianteService iEstudianteService;

    @GetMapping("")
    public List<Estudiante> findAll(){
        return iEstudianteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id) {

        Estudiante estudiante= null;
        Map<String, Object> response = new HashMap<>();

        try {
            estudiante = iEstudianteService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar la consulta del estudiante en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(estudiante == null) {
            response.put("message", "El estudiante con id " + id + " no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Estudiante>(estudiante, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody EstudianteDTO estudianteDTO){
        Estudiante estudianteNuevo = null;
        Estudiante estudiante = estudianteDTO.cambiarAEstudiante();
        Map<String, Object> response = new HashMap<>();

        try {
            estudianteNuevo =  iEstudianteService.save(estudiante);
        } catch (DataAccessException e) {
            response.put("message", "Error al insertar el estudiante");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Estudiante creado correctamente");
        response.put("estudiante", estudianteNuevo.cambiarAEstudianteDTO() );
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody EstudianteDTO estudianteDTO, @PathVariable int id) {

        Estudiante estudiante = estudianteDTO.cambiarAEstudiante();
        Estudiante estudianteActual = iEstudianteService.findById(id);
        Estudiante estudianteActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if(estudianteActual == null) {
            response.put("message", "Error al realizar la consulta del estudiante en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            estudianteActual.setNombre(estudiante.getNombre());
            estudianteActual.setCorreo(estudiante.getCorreo());
            estudianteActual.setCedula(estudiante.getCedula());
            estudianteActual.setPrograma(estudiante.getPrograma());

            estudianteActualizado = iEstudianteService.save(estudianteActual);
        } catch (DataAccessException e) {
            response.put("message", "error al modificar el estudiante");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "estudiante modificado con exito");
        response.put("estudiante", estudianteActualizado.cambiarAEstudianteDTO());
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        Map<String, Object> response = new HashMap<>();
        try {
            iEstudianteService.delete(id);
        }catch (DataAccessException e) {
            response.put("message", "error al eliminar el estudiante");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "estudiante eliminado correctamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
