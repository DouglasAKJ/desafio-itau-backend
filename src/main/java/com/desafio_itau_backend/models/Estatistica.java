package com.desafio_itau_backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Estatistica {
    @Getter
    @Setter
    long count;
    @Getter
    @Setter
    double sum;
    @Getter
    @Setter
    double avg;
    @Getter
    @Setter
    double min;
    @Getter
    @Setter
    double max;
}
