package com.example.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.backend.dto.ClienteDTO;
import com.example.backend.security.dto.MensajeDTO;
import com.example.backend.security.dto.ResponseMensaje;
import com.example.backend.service.ClienteService;

@Controller
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

	private ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	public ResponseEntity<ResponseMensaje<?>> create(@RequestBody ClienteDTO clienteDTO) {
		return new ResponseEntity<>(clienteService.create(clienteDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAll() {
		return ResponseEntity.ok(clienteService.getAll());
	}

	@GetMapping("/{id_cliente}")
	public ResponseEntity<ClienteDTO> getById(@PathVariable(name = "id_cliente") Long id_cliente) {
		return ResponseEntity.ok(clienteService.getById(id_cliente));
	}

	@PutMapping("/{id_cliente}")
	public ResponseEntity<ResponseMensaje<?>> update(@RequestBody ClienteDTO clienteDTO,
			@PathVariable(name = "id_cliente") Long id_cliente) {
		return new ResponseEntity<>(clienteService.update(id_cliente, clienteDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id_cliente}")
	public ResponseEntity<String> delete(@PathVariable(name = "id_cliente") Long id_cliente) {
		clienteService.deleteById(id_cliente);
		return new ResponseEntity(new MensajeDTO("Cliente eliminado"), HttpStatus.OK);
	}

}
