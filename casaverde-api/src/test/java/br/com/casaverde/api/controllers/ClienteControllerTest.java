package br.com.casaverde.api.controllers;

import br.com.casaverde.api.dtos.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void criaClienteValido() throws Exception {
        ClienteDTO dto = new ClienteDTO();
        dto.nome = "Joao";
        dto.email = "joao@mail.com";
        dto.senha = "segredo";

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void loginCliente() throws Exception {
        ClienteDTO c = new ClienteDTO();
        c.nome = "Ana";
        c.email = "ana@mail.com";
        c.senha = "segredo";

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(c)))
                .andExpect(status().isOk());

        ClienteDTO login = new ClienteDTO();
        login.email = "ana@mail.com";
        login.senha = "segredo";

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(login)))
                .andExpect(status().isOk());
    }

    @Test
    void criaELista() throws Exception {
        ClienteDTO dto = new ClienteDTO();
        dto.nome = "Teste";
        dto.email = "teste@email.com";
        dto.senha = "123";

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Teste"));
    }
}

