package com.uss.minimarketspring.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uss.minimarketspring.entity.Persona;
import com.uss.minimarketspring.exceptions.GeneralServiceException;
import com.uss.minimarketspring.exceptions.NoDataFoundException;
import com.uss.minimarketspring.exceptions.ValidateServiceException;
import com.uss.minimarketspring.repository.PersonaRepository;
import com.uss.minimarketspring.service.PersonaService;
import com.uss.minimarketspring.validators.PersonaValidator;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@Service
public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	private PersonaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		try {
			return repository.findAll();
		
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findById(int id) {
		try {
			return repository.findById(id).orElse(null);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findByNumDoc(String nrodoc) {
		try {
			return repository.findByNumDoc(nrodoc);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> findByNombreContaining(String nombre) {
		try {
			return repository.findByNombreContaining(nombre);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Persona create(Persona obj) {
		try {
			//Validación
			PersonaValidator.save(obj);
			Persona persona = findByNumDoc(obj.getNumDoc());
			if(persona!=null) {
				throw new ValidateServiceException("Ya hay un registro con el numero del documento");
			}
			//Guardamos
			obj.setEstado(true);
			return repository.save(obj);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
		
	}

	@Override
	@Transactional
	public Persona update(Persona obj) {
		try {
			PersonaValidator.save(obj);
			Persona personaDb=findById(obj.getIdPersona());
			if(personaDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validación de nombre repetido
			Persona persona=findByNumDoc(obj.getNumDoc());
			if(persona!=null && obj.getIdPersona()!=persona.getIdPersona()) {
				throw new ValidateServiceException("Ya hay un registro con ese DNI");
			}
			//Actualizamos la categoría
			personaDb.setNombre(obj.getNombre());
			personaDb.setApellido(obj.getApellido());
			personaDb.setCorreo(obj.getCorreo());
			personaDb.setDireccion(obj.getDireccion());
			personaDb.setNumDoc(obj.getNumDoc());
			personaDb.setTelefono(obj.getTelefono());
			personaDb.setFechaNacimiento(obj.getFechaNacimiento());
			
			
			return repository.save(personaDb);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public int delete(int id) {
		try {
			Persona personaDb= findById(id);
			if(personaDb==null) {
				return 0;
			}else {
				personaDb.setEstado(false);
				repository.save(personaDb);
				return 1;
			}
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
