package com.uss.minimarketspring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uss.minimarketspring.entity.Categoria;
import com.uss.minimarketspring.entity.Persona;
import com.uss.minimarketspring.exceptions.GeneralServiceException;
import com.uss.minimarketspring.exceptions.NoDataFoundException;
import com.uss.minimarketspring.exceptions.ValidateServiceException;
import com.uss.minimarketspring.repository.CategoriaRepository;
import com.uss.minimarketspring.service.CategoriaService;
import com.uss.minimarketspring.validators.CategoriaValidator;
import com.uss.minimarketspring.validators.PersonaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
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
	public Categoria findById(int id) {
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
	public Categoria findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
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
	public List<Categoria> findByNombreContaining(String nombre) {
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
	public Categoria create(Categoria obj) {
		try {
			//Validación
			CategoriaValidator.save(obj);
			Categoria categoria = findByNombre(obj.getNombre());
			if(categoria!=null) {
				throw new ValidateServiceException("Ya hay un registro de la misma categoria ");
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
	public Categoria update(Categoria obj) {
		try {
			CategoriaValidator.save(obj);
			Categoria categoriaDb=findById(obj.getIdCategoria());
			if(categoriaDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validación de nombre repetido
			Categoria categoria=findByNombre(obj.getNombre());
			if(categoria!=null && obj.getIdCategoria()!=categoria.getIdCategoria()) {
				throw new ValidateServiceException("Ya hay un registro con ese DNI");
			}
			//Actualizamos la categoría
			categoriaDb.setNombre(obj.getNombre());
		
			return repository.save(categoriaDb);
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
			Categoria categoriaDb= findById(id);
			if(categoriaDb==null) {
				return 0;
			}else {
				categoriaDb.setEstado(false);
				repository.save(categoriaDb);
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
