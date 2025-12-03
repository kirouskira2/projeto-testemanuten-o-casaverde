package br.com.casaverde.api.controllers;

import br.com.casaverde.api.dtos.ImovelDTO;
import br.com.casaverde.api.dtos.PrecoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ImovelControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;

    @Test
    void criaEAtualizaPreco() throws Exception {
        ImovelDTO dto = new ImovelDTO();
        dto.titulo = "Apto";
        dto.endereco = "Rua 1";
        dto.preco = 100.0;
        dto.quartos = 2;

        String body = mapper.writeValueAsString(dto);
        String res = mockMvc.perform(post("/api/imoveis").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        long id = mapper.readTree(res).get("id").asLong();

        PrecoDTO preco = new PrecoDTO();
        preco.novoPreco = 120.0;

        mockMvc.perform(put("/api/imoveis/" + id + "/preco")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(preco)))
                .andExpect(status().isOk());
    }

    @Test
    void criaELista() throws Exception {
        ImovelDTO dto = new ImovelDTO();
        dto.titulo = "Casa Teste";
        dto.endereco = "Rua Teste";
        dto.preco = 1000.0;
        dto.quartos = 2;

        String res = mockMvc.perform(post("/api/imoveis")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        long id = mapper.readTree(res).get("id").asLong();

        mockMvc.perform(get("/api/imoveis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Casa Teste"));
    }
}

