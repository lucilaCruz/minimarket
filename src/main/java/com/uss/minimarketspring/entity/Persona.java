package com.uss.minimarketspring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="persona")
@EntityListeners(AuditingEntityListener.class)

public class Persona {

	@Id
	@Column(name="idPersona",columnDefinition="smallint")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersona ;
	
	@Column(name="nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column(name="apellido", nullable = false, length = 100)
	private String apellido;
	
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name="numDoc", nullable = false, length = 15)
	private String numDoc;
	
	@Column(name="direccion", nullable = true, length = 255)
	private String direccion;
	
	@Column(name="correo", nullable = true, length = 100)
	private String correo;
	
	@Column(name="telefono", nullable = true, length = 20)
	private String telefono;
	
	
	@Column(name="estado",nullable=false)
	private boolean estado;
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="updated_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	
}
