package com.example.backend.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.example.backend.model.Cliente;
import com.example.backend.security.repository.GenericRepository;
@Repository
public interface ClienteRepository extends GenericRepository<Cliente, Long> {
	Optional<Cliente> findByNombreAndApellidopaternoAndApellidomaterno(String nombre, String apellidopaterno, String apellidomaterno);
	Optional<Cliente> findByDireccion(String direccion);
}
