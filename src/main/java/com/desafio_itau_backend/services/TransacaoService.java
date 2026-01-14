package com.desafio_itau_backend.services;

import com.desafio_itau_backend.models.Estatistica;
import com.desafio_itau_backend.models.Transacao;
import com.desafio_itau_backend.repository.TransacaoList;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class TransacaoService {
    List<Transacao> transacaoList = new ArrayList<>();

    public void adicionarTransacao(Transacao transacao){
        transacaoList.add(transacao);
    }

    public List<Transacao> retornaTransacoes(Integer intervaloSegundos){

        OffsetDateTime dataIntervalo = OffsetDateTime.now().minusSeconds(intervaloSegundos);

        return transacaoList.stream().filter(transacao -> transacao.getDataHora().isAfter(dataIntervalo)).toList();
    }

    public void deletaTransacoes(){
        transacaoList.clear();
    }

    public Estatistica criaEstatistica(){
        DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();

        if(transacaoList.isEmpty()){
            return new Estatistica(0L, 0.0, 0.0, 0.0, 0.0);
        } else {

            for (int i = transacaoList.size() - 1; i >= 0 && transacaoList.get(i).getDataHora().isAfter(OffsetDateTime.now().minusSeconds(60)); i--) {
                doubleSummaryStatistics.accept(transacaoList.get(i).getValor());
            }


            return new Estatistica(doubleSummaryStatistics.getCount(), doubleSummaryStatistics.getSum(), doubleSummaryStatistics.getAverage(), doubleSummaryStatistics.getMin(), doubleSummaryStatistics.getMax());
        }
    }

}
