package br.com.casaverde.api.controllers;

import br.com.casaverde.api.dtos.ImovelDTO;
import br.com.casaverde.api.dtos.PrecoDTO;
import br.com.casaverde.api.services.ImovelServiceApi;
import br.com.casaverde.model.Imovel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5174")
public class ImovelController {
    private final ImovelServiceApi service;

    public ImovelController(ImovelServiceApi service) { this.service = service; }

    @PostMapping("/imoveis")
    public ResponseEntity<?> criar(@RequestBody ImovelDTO dto) {
        long id = service.criar(dto.titulo, dto.endereco, dto.preco, dto.quartos);
        if (id == 0L) return ResponseEntity.badRequest().body(Map.of("erro", "dados invalidos"));
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/imoveis")
    public ResponseEntity<List<Imovel>> listar() { return ResponseEntity.ok(service.listar()); }

    @PutMapping("/imoveis/{id}/preco")
    public ResponseEntity<?> atualizarPreco(@PathVariable long id, @RequestBody PrecoDTO dto) {
        boolean ok = service.atualizarPreco(id, dto.novoPreco);
        return ok ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/imoveis/{id}/ativar")
    public ResponseEntity<?> ativar(@PathVariable long id) {
        return service.ativar(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/imoveis/{id}/desativar")
    public ResponseEntity<?> desativar(@PathVariable long id) {
        return service.desativar(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}

