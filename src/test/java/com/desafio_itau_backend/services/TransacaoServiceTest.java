package com.desafio_itau_backend.services;

import com.desafio_itau_backend.models.Estatistica;
import com.desafio_itau_backend.models.Transacao;
import com.desafio_itau_backend.repository.TransacaoList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.assertj.core.api.Assertions;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    Transacao transacao;

    MockMvc mockMvc;

    @BeforeEach
    void setup(){
        transacao = new Transacao(Math.random(), OffsetDateTime.now());
    }

    @Test
    void adicionaTransacaoValid(){

        transacaoService.adicionarTransacao(transacao);


        List<Transacao> transacoes = transacaoService.retornaTransacoes(60);

        assertTrue(transacoes.contains(transacao));

    }

    @Test
    void adicionaTransacaoInvalid(){

    }

    @Test
    void deletaTransacaoValid(){
        transacaoService.deletaTransacoes();

        assertTrue(transacaoService.transacaoList.isEmpty());
    }

    @Test
    void criaEstatisticaWithData(){

        transacaoService.adicionarTransacao(transacao);

        Estatistica estatisticaVazia = new Estatistica(0L, 0.0, 0.0, 0.0, 0.0);

        Estatistica estatistica = transacaoService.criaEstatistica();

        assertNotEquals(estatistica, estatisticaVazia);

    }

    @Test
    void criaEstatisticaWithoutData(){

        Estatistica estatistica = transacaoService.criaEstatistica();


        assertTrue(estatistica.getCount() == 0);
    }

}
