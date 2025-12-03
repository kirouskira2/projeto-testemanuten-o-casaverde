package br.com.casaverde.api.repositories;

import br.com.casaverde.model.Imovel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ImovelRepositoryImpl implements ImovelRepository {
    private final Map<Long, Imovel> store = new ConcurrentHashMap<>();
    private final AtomicLong ids = new AtomicLong(1);

    @Override
    public long save(Imovel imovel) {
        long id = ids.getAndIncrement();
        store.put(id, imovel);
        return id;
    }

    @Override
    public Optional<Imovel> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Imovel> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean updatePreco(long id, double novoPreco) {
        Imovel i = store.get(id);
        if (i == null) return false;
        i.setPreco(novoPreco);
        return true;
    }

    @Override
    public boolean ativar(long id) {
        Imovel i = store.get(id);
        if (i == null) return false;
        i.setAtivo(true);
        return true;
    }

    @Override
    public boolean desativar(long id) {
        Imovel i = store.get(id);
        if (i == null) return false;
        i.setAtivo(false);
        return true;
    }
}

