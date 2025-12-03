package br.com.casaverde.api.controllers;

import br.com.casaverde.api.dtos.ClienteDTO;
import br.com.casaverde.api.services.ClienteServiceApi;
import br.com.casaverde.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5174")
public class ClienteController {
    private final ClienteServiceApi service;

    public ClienteController(ClienteServiceApi service) {
        this.service = service;
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> criar(@RequestBody ClienteDTO dto) {
        Cliente c = service.criar(dto.nome, dto.email, dto.senha);
        if (c == null) return ResponseEntity.badRequest().body(Map.of("erro", "dados invalidos"));
        return ResponseEntity.ok(c);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClienteDTO dto) {
        boolean ok = service.login(dto.email, dto.senha);
        return ResponseEntity.ok(Map.of("ok", ok));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}

