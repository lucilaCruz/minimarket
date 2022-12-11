package com.uss.minimarketspring.service;

import java.util.List;

import com.uss.minimarketspring.entity.Persona;

public  interface PersonaService {
	public List<Persona> findAll();
	public Persona findById(int id);
	public Persona findByNumDoc(String nrodoc);
	public List<Persona> findByNombreContaining(String nombre);
    public Persona create(Persona obj);
    public Persona update(Persona obj);
    public int delete(int id);
}


