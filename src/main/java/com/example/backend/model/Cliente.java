package com.example.backend.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;
	
	@Column(name = "nombre", nullable = false, unique = true)
	@NotNull(message = "Debe ingresar un nombre")
	private String nombre;

	@Column(name = "apellidopaterno", nullable = false)
	@NotNull(message = "Debe ingresar apellido paterno")
	private String apellidopaterno;
	
	@Column(name = "apellidomaterno", nullable = false)
	@NotNull(message = "Debe ingresar apellido materno")
	private String apellidomaterno;

	@Column(name = "sexo", nullable = false)
	@NotNull(message = "Debe ingresar sexo")
	private Boolean sexo;

	@Column(name = "fechanacimiento", nullable = false)
  @NotNull(message = "Debe ingresar una fecha de nacimiento")
  private Date fechanacimiento;
	
	@Column(name = "direccion", nullable = false)
	@NotNull(message = "Debe ingresar una direccion")
	private String direccion;
	
	@Column(name = "correoelectronico", nullable = false)
	@NotNull(message = "Debe ingresar un correo electronico")
	private String correoelectronico;



	



}
