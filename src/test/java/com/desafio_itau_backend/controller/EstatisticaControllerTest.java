package com.desafio_itau_backend.controller;

import com.desafio_itau_backend.services.TransacaoService;
import com.desafio_itau_backend.services.TransacaoServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EstatisticaControllerTest {

    @InjectMocks
    EstatisticaController estatisticaController;

    @Mock
    TransacaoService transacaoService;


    MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticaController).build();

    }

    @Test
    void retornaEstatisticaValida() throws Exception{
        mockMvc.perform(get("/estatistica")).andExpect(status().isOk());
    }
}
