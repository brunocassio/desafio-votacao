package com.sicredi.desafiovotacao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssociadoDTO {
    private String nome;
    private String cpf;
}
