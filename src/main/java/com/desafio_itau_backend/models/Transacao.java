package com.desafio_itau_backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    @Getter
    @Setter
    double valor;
    @Getter
    @Setter
    OffsetDateTime dataHora;


    @Override
    public boolean equals(Object transacao){
        if (this == transacao){
            return true;
        }
        if (transacao == null || getClass() != transacao.getClass()) return false;

        Transacao that = (Transacao) transacao;

        return Double.compare(that.valor, valor) == 0 && Objects.equals(dataHora, that.dataHora);

    }

    @Override
    public int hashCode(){
        return Objects.hash(valor, dataHora);
    }
}
