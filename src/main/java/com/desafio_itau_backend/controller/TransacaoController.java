package com.desafio_itau_backend.controller;

import com.desafio_itau_backend.dto.TransacaoDTO;
import com.desafio_itau_backend.models.Transacao;
import com.desafio_itau_backend.repository.TransacaoList;
import com.desafio_itau_backend.services.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
     TransacaoService transacaoService;

     TransacaoController(TransacaoService transacaoService){
         this.transacaoService = transacaoService;
     }

    @GetMapping
    ArrayList<Transacao> retornaTransacoes(){
        return transacaoService.retornaTransacoes();
    }

    @PostMapping
    ResponseEntity<?> recebeTransacao(@RequestBody TransacaoDTO transacaoDTO){
        try{
            if (transacaoDTO.dataHora().isBefore(OffsetDateTime.now()) && transacaoDTO.valor() >= 0 ){
                Transacao transacao = new Transacao(transacaoDTO.valor(), transacaoDTO.dataHora());
                transacaoService.adicionarTransacao(transacao);
                return ResponseEntity.status(201).build();
            } else {
                return ResponseEntity.status(422).build();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    ResponseEntity<?> deletaTransacao(){
        transacaoService.deletaTransacoes();
        return ResponseEntity.ok().build();
    }
}
