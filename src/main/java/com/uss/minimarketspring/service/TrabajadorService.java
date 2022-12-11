package com.uss.minimarketspring.service;

import java.util.List;

import com.uss.minimarketspring.entity.Trabajador;



public interface TrabajadorService {
	public List<Trabajador> findAll();
	public Trabajador findById(int id);
	public Trabajador findByCargo(String nombre);
    public Trabajador create(Trabajador obj);
    public Trabajador update(Trabajador obj);
    public int delete(int id);

}
