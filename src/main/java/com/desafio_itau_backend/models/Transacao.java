package com.desafio_itau_backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    @Getter
    @Setter
    double valor;
    @Getter
    @Setter
    OffsetDateTime dataHora;


}
