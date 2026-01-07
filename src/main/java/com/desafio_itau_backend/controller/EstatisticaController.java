package com.desafio_itau_backend.controller;

import com.desafio_itau_backend.repository.TransacaoList;
import com.desafio_itau_backend.services.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    TransacaoService transacaoService;

    public EstatisticaController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    ResponseEntity<?> retornaEstatistica(){
        return ResponseEntity.ok(transacaoService.criaEstatistica());
    }


}
