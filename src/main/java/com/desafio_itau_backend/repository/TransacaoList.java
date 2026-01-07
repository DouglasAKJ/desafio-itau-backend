package com.desafio_itau_backend.repository;

import com.desafio_itau_backend.models.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
public class TransacaoList extends ArrayList<Transacao>{
    @Getter
    @Setter
    ArrayList<Transacao> transacaoArrayList;
}
