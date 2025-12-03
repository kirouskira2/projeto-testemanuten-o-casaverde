package br.com.casaverde.api.repositories;

import br.com.casaverde.model.Visita;
import java.util.Optional;

public interface VisitaRepository {
    long save(Visita visita);
    Optional<Visita> findById(long id);
}

