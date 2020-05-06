package com.api.spring.springbootapp.models.restcontroller;

import com.api.spring.springbootapp.models.entity.Programa;
import com.api.spring.springbootapp.models.services.IProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/programas")
public class ProgramaRestController {

    @Autowired
    private IProgramaService iProgramaService;

    @GetMapping("")
    public List<Programa> findAll(){
        return iProgramaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id) {

        Programa programa= null;
        Map<String, Object> response = new HashMap<>();

        try {
            programa = iProgramaService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar la consulta del programa en la base de datos");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(programa == null) {
            response.put("message", "El programa con id " + id + " no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Programa>(programa, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Programa programa){
        Programa programaNuevo = null;
        Map<String, Object> response = new HashMap<>();

        try {
            programaNuevo =  iProgramaService.save(programa);
        } catch (DataAccessException e) {
            response.put("message", "Error al insertar el programa");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Programa creado correctamente");
        response.put("programa", programaNuevo );
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Programa programa, @PathVariable int id) {

        Programa programaActual = iProgramaService.findById(id);
        Programa programaActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if(programaActual == null) {
            response.put("message", "Error al realizar la consulta del programa en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            programaActual.setNombre(programa.getNombre());
            programaActual.setDescripcion(programa.getDescripcion());

            programaActualizado = iProgramaService.save(programaActual);
        } catch (DataAccessException e) {
            response.put("message", "error al modificar el programa");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Programa modificado con exito");
        response.put("programa", programaActualizado );
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        Map<String, Object> response = new HashMap<>();
        try {
            iProgramaService.delete(id);
        }catch (DataAccessException e) {
            response.put("message", "error al eliminar el programa");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Programa eliminada correctamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
