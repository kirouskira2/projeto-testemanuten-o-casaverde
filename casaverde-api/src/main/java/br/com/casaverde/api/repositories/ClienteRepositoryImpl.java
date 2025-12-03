package br.com.casaverde.api.repositories;

import br.com.casaverde.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
    private final Map<String, Cliente> store = new ConcurrentHashMap<>();

    @Override
    public Cliente save(Cliente cliente) {
        store.put(cliente.getEmail(), cliente);
        return cliente;
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return Optional.ofNullable(store.get(email));
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(store.values());
    }
}

