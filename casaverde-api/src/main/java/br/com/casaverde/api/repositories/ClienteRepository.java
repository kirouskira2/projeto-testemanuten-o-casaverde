package br.com.casaverde.api.repositories;

import br.com.casaverde.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findAll();
}

