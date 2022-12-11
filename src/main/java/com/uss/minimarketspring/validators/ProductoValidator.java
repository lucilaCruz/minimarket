package com.uss.minimarketspring.validators;

import com.uss.minimarketspring.entity.Producto;
import com.uss.minimarketspring.exceptions.ValidateServiceException;

public class ProductoValidator {
	public static void save(Producto producto) {
		if(producto.getNombre()==null || producto.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(producto.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy extenso");
		}
		if(producto.getPrecio_venta()==null) {
			throw new ValidateServiceException("El precio es requerido");
		}
		if(producto.getPrecio_venta()<0) {
			throw new ValidateServiceException("El precio no puede ser negativo");
		}
	}

}
