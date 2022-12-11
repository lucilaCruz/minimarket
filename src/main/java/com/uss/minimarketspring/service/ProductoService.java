package com.uss.minimarketspring.service;

import java.util.List;

import com.uss.minimarketspring.entity.Producto;


public interface ProductoService {
	public List<Producto> findAll();
	public Producto findById(int id);
	public Producto findByNombre(String nombre);
	public List<Producto> findByNombreContaining(String nombre);
    public Producto create(Producto obj);
    public Producto update(Producto obj);
    public int delete(int id);

}
