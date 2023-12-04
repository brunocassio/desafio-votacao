package com.sicredi.desafiovotacao.converter;

import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.model.Pauta;

public class PautaConverter {

    public static Pauta convertToEntity(PautaDTO dto) {
        return Pauta.builder()
                .votacaoAberta(dto.isVotacaoAberta())
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .build();
    }
}
