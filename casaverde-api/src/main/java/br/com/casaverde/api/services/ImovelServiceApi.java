package br.com.casaverde.api.services;

import br.com.casaverde.model.Imovel;
import br.com.casaverde.service.ImovelService;
import br.com.casaverde.api.repositories.ImovelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelServiceApi {
    private final ImovelService core;
    private final ImovelRepository repo;

    public ImovelServiceApi(ImovelRepository repo) {
        this.core = new ImovelService();
        this.repo = repo;
    }

    public long criar(String titulo, String endereco, double preco, int quartos) {
        Imovel i = core.criarImovel(titulo, endereco, preco, quartos);
        if (i == null) return 0L;
        return repo.save(i);
    }

    public List<Imovel> listar() { return repo.findAll(); }

    public boolean atualizarPreco(long id, double novoPreco) {
        Optional<Imovel> opt = repo.findById(id);
        if (opt.isEmpty()) return false;
        return core.atualizarPreco(opt.get(), novoPreco);
    }

    public boolean ativar(long id) {
        Optional<Imovel> opt = repo.findById(id);
        if (opt.isEmpty()) return false;
        core.ativar(opt.get());
        return true;
    }

    public boolean desativar(long id) {
        Optional<Imovel> opt = repo.findById(id);
        if (opt.isEmpty()) return false;
        core.desativar(opt.get());
        return true;
    }
}

