package com.uss.minimarketspring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uss.minimarketspring.entity.Trabajador;
import com.uss.minimarketspring.exceptions.GeneralServiceException;
import com.uss.minimarketspring.exceptions.NoDataFoundException;
import com.uss.minimarketspring.exceptions.ValidateServiceException;
import com.uss.minimarketspring.repository.TrabajadorRepository;
import com.uss.minimarketspring.service.TrabajadorService;
import com.uss.minimarketspring.validators.TrabajadorValidator;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class TrabajadorServiceImpl implements TrabajadorService {
	
	@Autowired
	private TrabajadorRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Trabajador> findAll() {
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
	public Trabajador findById(int id) {
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
	public Trabajador findByCargo(String cargo) {
		try {
			return repository.findByCargo(cargo);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
		
	}

	@Override
	public Trabajador create(Trabajador obj) {
		try {
			//Validación
			TrabajadorValidator.save(obj);
			Trabajador trabajador = findByCargo(obj.getCargo());
			if(trabajador!=null) {
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
	public Trabajador update(Trabajador obj) {
		try {
			TrabajadorValidator.save(obj);
			Trabajador trabajadorDb=findById(obj.getId());
			if(trabajadorDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validación de idpersona y de cargo
			
			//Actualizamos la trabjador
			trabajadorDb.setCargo(obj.getCargo());
			trabajadorDb.setPago_mensual(obj.getPago_mensual());
			trabajadorDb.setPersona(obj.getPersona());
			return repository.save(trabajadorDb);
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
			Trabajador trabajadorDb= findById(id);
			if(trabajadorDb==null) {
				return 0;
			}else {
				trabajadorDb.setEstado(false);
				repository.save(trabajadorDb);
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
