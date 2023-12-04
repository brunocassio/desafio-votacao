package com.sicredi.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoVotacaoDTO {
    private long count;
    private String opcao;
}
