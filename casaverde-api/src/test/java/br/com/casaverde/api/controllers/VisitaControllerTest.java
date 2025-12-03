package br.com.casaverde.api.controllers;

import br.com.casaverde.api.dtos.VisitaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VisitaControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;

    @Test
    void criaEConfirma() throws Exception {
        VisitaDTO dto = new VisitaDTO();
        dto.clienteId = 1L;
        dto.imovelId = 1L;
        dto.dataHora = LocalDateTime.now().plusDays(1).toString();

        String res = mockMvc.perform(post("/api/visitas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        long id = mapper.readTree(res).get("id").asLong();

        mockMvc.perform(put("/api/visitas/" + id + "/confirmar"))
                .andExpect(status().isOk());
    }
}

