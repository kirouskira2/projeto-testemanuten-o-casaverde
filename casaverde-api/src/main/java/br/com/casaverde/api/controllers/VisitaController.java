package br.com.casaverde.api.controllers;

import br.com.casaverde.api.dtos.ReagendarDTO;
import br.com.casaverde.api.dtos.VisitaDTO;
import br.com.casaverde.api.services.VisitaServiceApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5174")
public class VisitaController {
    private final VisitaServiceApi service;

    public VisitaController(VisitaServiceApi service) { this.service = service; }

    @PostMapping("/visitas")
    public ResponseEntity<?> criar(@RequestBody VisitaDTO dto) {
        LocalDateTime dt = LocalDateTime.parse(dto.dataHora);
        long id = service.criar(dto.clienteId, dto.imovelId, dt);
        if (id == 0L) return ResponseEntity.badRequest().body(Map.of("erro", "dados invalidos"));
        return ResponseEntity.ok(Map.of("id", id));
    }

    @PutMapping("/visitas/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable long id) {
        return service.cancelar(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/visitas/{id}/confirmar")
    public ResponseEntity<?> confirmar(@PathVariable long id) {
        return service.confirmar(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/visitas/{id}/reagendar")
    public ResponseEntity<?> reagendar(@PathVariable long id, @RequestBody ReagendarDTO dto) {
        LocalDateTime dt = LocalDateTime.parse(dto.novaDataHora);
        return service.reagendar(id, dt) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}

