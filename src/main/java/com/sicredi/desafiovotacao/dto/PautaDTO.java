package com.sicredi.desafiovotacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaDTO {
    private String titulo;
    private String descricao;
    @Schema(description = "Campo que informa se a votação está aberta", defaultValue = "false")
    private boolean votacaoAberta;
}
