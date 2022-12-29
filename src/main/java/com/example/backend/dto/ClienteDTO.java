package com.example.backend.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private int id_cliente;
	@NotEmpty
	@Size(max=255, message = "Nombre puede tener hasta 255 caracteres")
	private String nombre;

	@NotEmpty
	@Size(max=255, message = "Apellido paterno puede tener hasta 255 caracteres")
	private String apellidopaterno;

	@NotEmpty
	@Size(max=255, message = "Apellido materno puede tener hasta 255 caracteres")
	private String apellidomaterno;

	@NotNull(message = "Sexo no puede ser vacío")
	private Boolean sexo;

	@NotNull(message = "Fecha de Nacimiento no puede ser vacío")
	private Date fechanacimiento;

	@NotEmpty
	@Size(max=255, message = "Direccion puede tener hasta 255 caracteres")
	private String direccion;

	@NotEmpty
	@Size(max=255, message = "Correo electronico puede tener hasta 255 caracteres")
	private String correoelectronico;
	
	private String fechanacimientotxt;
	

	
}
