package com.example.backend.service;

import java.util.List;

import com.example.backend.dto.ClienteDTO;
import com.example.backend.security.dto.ResponseMensaje;

public interface ClienteService {

	ResponseMensaje<?> create(ClienteDTO clienteDTO);

	List<ClienteDTO> getAll();

	ClienteDTO getById(Long id_cliente);

	ResponseMensaje<?> update(Long id_cliente, ClienteDTO clienteDTO);

	void deleteById(Long id_cliente);

}
