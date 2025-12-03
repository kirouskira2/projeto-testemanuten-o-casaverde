package br.com.casaverde.api.services;

import br.com.casaverde.model.Visita;
import br.com.casaverde.service.VisitaService;
import br.com.casaverde.api.repositories.VisitaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VisitaServiceApi {
    private final VisitaService core;
    private final VisitaRepository repo;

    public VisitaServiceApi(VisitaRepository repo) {
        this.core = new VisitaService();
        this.repo = repo;
    }

    public long criar(Long clienteId, Long imovelId, LocalDateTime dataHora) {
        Visita v = core.criarVisita(clienteId, imovelId, dataHora);
        if (v == null) return 0L;
        return repo.save(v);
    }

    public boolean cancelar(long id) {
        Optional<Visita> v = repo.findById(id);
        return v.isPresent() && core.cancelar(v.get());
    }

    public boolean confirmar(long id) {
        Optional<Visita> v = repo.findById(id);
        return v.isPresent() && core.confirmar(v.get());
    }

    public boolean reagendar(long id, LocalDateTime novaDataHora) {
        Optional<Visita> v = repo.findById(id);
        return v.isPresent() && core.reagendar(v.get(), novaDataHora);
    }
}

