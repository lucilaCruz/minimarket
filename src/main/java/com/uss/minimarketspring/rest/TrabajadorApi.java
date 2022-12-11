package com.uss.minimarketspring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uss.minimarketspring.entity.Persona;
import com.uss.minimarketspring.entity.Trabajador;
import com.uss.minimarketspring.service.PersonaService;
import com.uss.minimarketspring.service.TrabajadorService;




@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorApi {
	@Autowired
	private TrabajadorService service;
	
	@Autowired
	private PersonaService servicePersona;
	
	@GetMapping()
	public ResponseEntity<List<Trabajador>> getAll(){
		List<Trabajador> trabajadores= service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(trabajadores);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Trabajador> getById(@PathVariable("id") int id) {
		Trabajador trabajador = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(trabajador);
	}
	
	@PostMapping
	public ResponseEntity<Trabajador> create(@RequestBody Trabajador trabajador) {
		Trabajador trabajadorDb=service.create(trabajador);
		return ResponseEntity.status(HttpStatus.CREATED).body(trabajadorDb);
	}
	
	@PutMapping
	public ResponseEntity<Trabajador> update(@RequestBody Trabajador trabajador) {
		Trabajador trabajadorDb=service.update(trabajador);
		return ResponseEntity.status(HttpStatus.OK).body(trabajadorDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
	
	@GetMapping("/personas")
    public List<Persona> getPersonas() {
        return servicePersona.findAll();
    }

}
