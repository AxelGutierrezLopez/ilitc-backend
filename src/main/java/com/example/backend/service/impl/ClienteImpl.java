package com.example.backend.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.backend.dto.ClienteDTO;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Cliente;
import com.example.backend.repository.ClienteRepository;
import com.example.backend.security.dto.ResponseMensaje;
import com.example.backend.service.ClienteService;
import org.modelmapper.ModelMapper;

@Transactional
@Service
public class ClienteImpl implements ClienteService {

	private ClienteRepository clienteRepository;
	private ModelMapper mapper;

	public ClienteImpl(ClienteRepository clienteRepository, ModelMapper mapper) {
		this.clienteRepository = clienteRepository;
		this.mapper = mapper;
	}

	private ResponseMensaje<?> validacion(Cliente cliente, ClienteDTO clienteDTO) {
		// VALIDACION REQUERIDO
		if (clienteDTO.getNombre() == null) {
			return new ResponseMensaje<>(404, "Nombres es requerido", null);
		}
		if (clienteDTO.getApellidopaterno() == null) {
			return new ResponseMensaje<>(404, "Apellido Paterno es requerido", null);
		}
		if (clienteDTO.getApellidomaterno() == null) {
			return new ResponseMensaje<>(404, "Apellido Materno es requerido", null);
		}
		if (clienteDTO.getFechanacimiento() == null) {
			return new ResponseMensaje<>(404, "Fecha de Nacimiento es requerido", null);
		}
		if (clienteDTO.getSexo() == null) {
			return new ResponseMensaje<>(404, "Sexo es requerido", null);
		}
		if (clienteDTO.getDireccion() == null) {
			return new ResponseMensaje<>(404, "Direccion es requerido", null);
		}
		if (clienteDTO.getCorreoelectronico() == null) {
			return new ResponseMensaje<>(404, "Correo Electronico es requerido", null);
		}

		if (cliente == null) {
			// CREAR
			if (this.clienteRepository.findByNombreAndApellidopaternoAndApellidomaterno(clienteDTO.getNombre(),
					clienteDTO.getApellidopaterno(), clienteDTO.getApellidomaterno()).orElse(null) != null) {
				return new ResponseMensaje<>(404, "Cliente ya existe " + clienteDTO.getNombre() + " "
						+ clienteDTO.getApellidopaterno() + " " + clienteDTO.getApellidomaterno(), null);
			}

			// CREAR OBJETO
			Cliente ent = mapToEntity(clienteDTO);
			Cliente newcliente = clienteRepository.save(ent);
			ClienteDTO clienteResponse = mapToDTO(newcliente);
			return new ResponseMensaje<>(200,
					"Cliente creado " + clienteDTO.getNombre() + " " + clienteDTO.getApellidopaterno()
							+ " " + clienteDTO.getApellidomaterno(),
					clienteResponse);

		} else {

			// UPDATE
			return null;
		}

	}

	@Override
	public ResponseMensaje<?> create(ClienteDTO clienteDTO) {
		return this.validacion(null, clienteDTO);
	}

	@Override
	public List<ClienteDTO> getAll() {
		List<Cliente> listOfclientees = clienteRepository.findAll();
		List<ClienteDTO> content = listOfclientees.stream().map(cliente -> mapToDTO(cliente))
				.collect(Collectors.toList());
		return content;
	}

	@Override
	public ClienteDTO getById(Long id_cliente) {
		Cliente enitdad = clienteRepository.findById(id_cliente)
				.orElseThrow(() -> new ResourceNotFoundException("cliente", "id_cliente", id_cliente));
		return mapToDTO(enitdad);
	}

	@Override
	public ResponseMensaje<?> update(Long id_cliente, ClienteDTO clienteDTO) {
		Cliente cliente = clienteRepository.findById(id_cliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id_cliente", id_cliente));
		return this.validacion(cliente, clienteDTO);
	}

	@Override
	public void deleteById(Long id_cliente) {
		Cliente cliente = clienteRepository.findById(id_cliente)
				.orElseThrow(() -> new ResourceNotFoundException("cliente", "id_cliente",
						id_cliente));

		this.clienteRepository.delete(cliente);
		
	}

	public ClienteDTO mapToDTO(Cliente cliente) {
		SimpleDateFormat sFecha = new SimpleDateFormat("dd/MM/yyyy");
		ClienteDTO clienteDTO = mapper.map(cliente, ClienteDTO.class);
		clienteDTO.setFechanacimientotxt(sFecha.format(cliente.getFechanacimiento()));
		return clienteDTO;
	}

	private Cliente mapToEntity(ClienteDTO clienteDTO) {
		Cliente cliente = mapper.map(clienteDTO, Cliente.class);
		return cliente;
	}

}
