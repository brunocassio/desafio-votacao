package com.sicredi.desafiovotacao.dto;

import lombok.Data;

@Data
public class PautaDTO {
    private String titulo;
    private String descricao;
    private boolean votacaoAberta;
}
