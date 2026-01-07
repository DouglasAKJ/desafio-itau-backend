package com.desafio_itau_backend.dto;

import java.time.OffsetDateTime;

public record TransacaoDTO(double valor, OffsetDateTime dataHora) {
}
