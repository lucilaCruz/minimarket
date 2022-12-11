package com.uss.minimarketspring.validators;

import com.uss.minimarketspring.entity.Trabajador;
import com.uss.minimarketspring.exceptions.ValidateServiceException;

public class TrabajadorValidator {
	public static void save(Trabajador trabajador) {
		
		if(trabajador.getCargo().length()>100) {
			throw new ValidateServiceException("El Cargo es muy extenso");
		}
		if(trabajador.getPago_mensual()==null) {
			throw new ValidateServiceException("El pago es requerido");
		}
		if(trabajador.getPago_mensual() <= 0) {
			throw new ValidateServiceException("El pago debe ser mayor a cero");
		}
	}

}
