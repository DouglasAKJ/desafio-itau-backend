package com.desafio_itau_backend.controller;

import com.desafio_itau_backend.dto.TransacaoDTO;
import com.desafio_itau_backend.models.Transacao;
import com.desafio_itau_backend.repository.TransacaoList;
import com.desafio_itau_backend.services.TransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/transacao")

public class TransacaoController {
     TransacaoService transacaoService;

     TransacaoController(TransacaoService transacaoService){
         this.transacaoService = transacaoService;
     }

    @PostMapping
    ResponseEntity<?> recebeTransacao(@RequestBody TransacaoDTO transacaoDTO){
         try{
             transacaoService.adicionarTransacao(transacaoDTO);
             log.info("Transação realizada com sucesso");
             return ResponseEntity.status(201).build();
         }catch (IllegalArgumentException e){
             log.info(e.getMessage());
             return ResponseEntity.status(422).build();
         }

    }

    @DeleteMapping
    ResponseEntity<?> deletaTransacao(){
        transacaoService.deletaTransacoes();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<?> retornaTransacoes(){
         return ResponseEntity.ok(transacaoService.retornaTransacoes(5000));
    }
}
