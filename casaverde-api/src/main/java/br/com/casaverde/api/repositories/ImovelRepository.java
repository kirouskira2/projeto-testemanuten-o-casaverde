package br.com.casaverde.api.repositories;

import br.com.casaverde.model.Imovel;
import java.util.List;
import java.util.Optional;

public interface ImovelRepository {
    long save(Imovel imovel);
    Optional<Imovel> findById(long id);
    List<Imovel> findAll();
    boolean updatePreco(long id, double novoPreco);
    boolean ativar(long id);
    boolean desativar(long id);
}

