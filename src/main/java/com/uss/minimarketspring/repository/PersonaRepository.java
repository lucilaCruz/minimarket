package com.uss.minimarketspring.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uss.minimarketspring.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository< Persona, Integer> {
	public Persona findByNombre(String nombre);
	public Persona findByNumDoc(String nrodoc);
	public List<Persona> findByNombreContaining(String nombre);
}
