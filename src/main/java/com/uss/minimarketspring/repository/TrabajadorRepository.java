package com.uss.minimarketspring.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uss.minimarketspring.entity.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador,Integer> {
	public Trabajador findByCargo(String cargo);

}
