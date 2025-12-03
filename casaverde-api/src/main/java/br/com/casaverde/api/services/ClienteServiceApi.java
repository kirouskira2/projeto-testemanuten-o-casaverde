package br.com.casaverde.api.services;

import br.com.casaverde.model.Cliente;
import br.com.casaverde.service.ClienteService;
import br.com.casaverde.api.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceApi {
    private final ClienteService core;
    private final ClienteRepository repository;

    public ClienteServiceApi(ClienteRepository repository) {
        this.core = new ClienteService();
        this.repository = repository;
    }

    public Cliente criar(String nome, String email, String senha) {
        if (repository.findByEmail(email).isPresent()) return null;
        Cliente criado = core.criarCliente(nome, email, senha);
        if (criado == null) return null;
        return repository.save(criado);
    }

    public boolean login(String email, String senha) {
        Optional<Cliente> c = repository.findByEmail(email);
        return c.isPresent() && core.autenticar(c.get(), senha) && c.get().isAtivo();
    }

    public java.util.List<Cliente> listar() {
        return repository.findAll();
    }
}

