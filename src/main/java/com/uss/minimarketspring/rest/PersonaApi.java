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
import com.uss.minimarketspring.service.PersonaService;






@RestController
@RequestMapping("/api/personas")
public class PersonaApi {
	@Autowired
	private PersonaService service;
	
	@GetMapping()
	public ResponseEntity<List<Persona>> getAll(){
		List<Persona> personas= service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(personas);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
		Persona persona = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(persona);
	}
	
	@PostMapping
	public ResponseEntity<Persona> create(@RequestBody Persona persona) {
		Persona personaDb=service.create(persona);
		return ResponseEntity.status(HttpStatus.CREATED).body(personaDb);
	}
	
	@PutMapping
	public ResponseEntity<Persona> update(@RequestBody Persona persona) {
		Persona personaDb=service.update(persona);
		return ResponseEntity.status(HttpStatus.OK).body(personaDb);
	}
	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
	
	

}
