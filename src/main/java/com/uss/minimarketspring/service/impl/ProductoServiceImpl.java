package com.uss.minimarketspring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uss.minimarketspring.entity.Producto;
import com.uss.minimarketspring.exceptions.GeneralServiceException;
import com.uss.minimarketspring.exceptions.NoDataFoundException;
import com.uss.minimarketspring.exceptions.ValidateServiceException;
import com.uss.minimarketspring.repository.ProductoRepository;
import com.uss.minimarketspring.service.ProductoService;
import com.uss.minimarketspring.validators.ProductoValidator;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
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
	public Producto findById(int id) {
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
	public Producto findByNombre(String nombre) {
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
	public List<Producto> findByNombreContaining(String nombre) {
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
	public Producto create(Producto obj) {
		try {
			//Validación
			ProductoValidator.save(obj);
			Producto producto=findByNombre(obj.getNombre());
			if(producto!=null) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
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
	public Producto update(Producto obj) {
		try {
			ProductoValidator.save(obj);
			Producto productoDb=findById(obj.getId());
			if(productoDb==null) {
				throw new ValidateServiceException("No hay un registro con ese ID");
			}
			//Validación de nombre repetido
			Producto producto=findByNombre(obj.getNombre());
			if(producto!=null && obj.getId()!=producto.getId()) {
				throw new ValidateServiceException("Ya hay un registro con ese nombre");
			}
			//Actualizamos la categoría
			productoDb.setNombre(obj.getNombre());
			productoDb.setPrecio_venta(obj.getPrecio_venta());
			productoDb.setPrecio_compra(obj.getPrecio_compra());
			productoDb.setDescripcion(obj.getDescripcion());
			productoDb.setCategoria(obj.getCategoria());
			
			return repository.save(productoDb);
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
			Producto productoDb= findById(id);
			if(productoDb==null) {
				return 0;
			}else {
				productoDb.setEstado(false);
				repository.save(productoDb);
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
