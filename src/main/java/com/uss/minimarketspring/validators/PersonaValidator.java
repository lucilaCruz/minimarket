package com.uss.minimarketspring.validators;

import com.uss.minimarketspring.entity.Persona;

import com.uss.minimarketspring.exceptions.ValidateServiceException;

public class PersonaValidator {
	public static void save(Persona persona) {
		if(persona.getNumDoc() == null || persona.getNumDoc().trim().isEmpty()) {
			throw new ValidateServiceException("El numero de documento es requerido");
		}
		if(persona.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(persona.getApellido() == null) {
			throw new ValidateServiceException("El apellidos es requerido");
		}
		
	}

}
