package com.desafio_itau_backend.services;

import com.desafio_itau_backend.models.Estatistica;
import com.desafio_itau_backend.models.Transacao;
import com.desafio_itau_backend.repository.TransacaoList;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

@Service
public class TransacaoService {
    TransacaoList transacaoList = new TransacaoList();

    public void adicionarTransacao(Transacao transacao){
        transacaoList.add(transacao);
    }

    public ArrayList<Transacao> retornaTransacoes(){
        return transacaoList;
    }

    public void deletaTransacoes(){
        transacaoList.clear();
    }

    public Estatistica criaEstatistica(){
        DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();

        for(int i = transacaoList.size() - 1; i>=0 && transacaoList.get(i).getDataHora().isAfter(OffsetDateTime.now().minusSeconds(60)) ; i-- ){
            doubleSummaryStatistics.accept(transacaoList.get(i).getValor());
        }
        return new Estatistica(doubleSummaryStatistics.getCount(), doubleSummaryStatistics.getSum(), doubleSummaryStatistics.getAverage(), doubleSummaryStatistics.getMin(), doubleSummaryStatistics.getMax());

    }
}
