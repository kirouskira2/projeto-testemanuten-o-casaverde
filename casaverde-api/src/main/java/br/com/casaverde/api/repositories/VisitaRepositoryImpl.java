package br.com.casaverde.api.repositories;

import br.com.casaverde.model.Visita;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class VisitaRepositoryImpl implements VisitaRepository {
    private final Map<Long, Visita> store = new ConcurrentHashMap<>();
    private final AtomicLong ids = new AtomicLong(1);

    @Override
    public long save(Visita visita) {
        long id = ids.getAndIncrement();
        store.put(id, visita);
        return id;
    }

    @Override
    public Optional<Visita> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }
}

