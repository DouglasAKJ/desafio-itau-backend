package com.desafio_itau_backend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.desafio_itau_backend.dto.TransacaoDTO;
import com.desafio_itau_backend.models.Transacao;
import com.desafio_itau_backend.services.TransacaoService;
import com.desafio_itau_backend.services.TransacaoServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;

@SpringBootTest
public class TransacaoTest {

    @InjectMocks
    TransacaoController transacaoController;

    @Mock
    TransacaoService transacaoService;

    @Autowired
    final ObjectMapper objectMapper = new ObjectMapper();

    TransacaoDTO transacao;

    MockMvc mockMvc;

    @BeforeEach
    void setup(){
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
        transacao = new TransacaoDTO(Math.random(), OffsetDateTime.now());
    }

    @Test
    void adicionaTransacaoValida() throws Exception{

        mockMvc.perform(post("/transacao")
                .content(objectMapper.writeValueAsString(transacao))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }

    @Test
    void geraErroAdicionaTransacao() throws Exception {

        Mockito.doThrow(new IllegalArgumentException("Transação inválida"))
                .when(transacaoService)
                .adicionarTransacao(Mockito.any());

        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());


    }

    @Test
    void deletaTransacoes() throws Exception {

        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());


    }
}
