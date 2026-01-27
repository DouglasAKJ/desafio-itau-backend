package com.desafio_itau_backend.services;

import com.desafio_itau_backend.dto.TransacaoDTO;
import com.desafio_itau_backend.models.Estatistica;
import com.desafio_itau_backend.models.Transacao;
import com.desafio_itau_backend.repository.TransacaoList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.assertj.core.api.Assertions;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TransacaoServiceTest {

    TransacaoService transacaoService;

    TransacaoDTO transacao;

    @BeforeEach
    void setup(){
        transacaoService = new TransacaoService();
        transacao = new TransacaoDTO(Math.random(), OffsetDateTime.now().minusSeconds(10));
    }

    @Test
    void adicionaTransacaoValid(){


        transacaoService.adicionarTransacao(transacao);

        List<Transacao> transacoes = transacaoService.retornaTransacoes(60);

        assertFalse(transacoes.isEmpty());
        assertEquals(transacao.valor(), transacoes.getFirst().getValor());


    }

    @Test
    void adicionaTransacaoInvalid(){

        TransacaoDTO transacaoDTO = new TransacaoDTO(100.00, OffsetDateTime.now().plusSeconds(100));

        assertThrows(IllegalArgumentException.class, () -> {
            transacaoService.adicionarTransacao(transacaoDTO);
        });

    }

    @Test
    void deletaTransacaoValid(){
        transacaoService.deletaTransacoes();

        assertTrue(transacaoService.transacaoList.isEmpty());
    }

    @Test
    void criaEstatisticaWithData() throws Exception{

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
