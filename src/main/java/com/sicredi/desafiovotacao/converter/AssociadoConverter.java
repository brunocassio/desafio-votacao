package com.sicredi.desafiovotacao.converter;

import com.sicredi.desafiovotacao.dto.AssociadoDTO;
import com.sicredi.desafiovotacao.model.Associado;

public class AssociadoConverter {

    public static Associado convertToEntity(AssociadoDTO dto) {
        return Associado.builder()
                .cpf(dto.getCpf())
                .nome(dto.getNome())
                .build();

    }
}
