package com.sicredi.desafiovotacao.util;

import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.model.Associado;
import com.sicredi.desafiovotacao.model.Pauta;
import com.sicredi.desafiovotacao.model.Voto;

public abstract class Util {

    public static Pauta getPauta() {
        return Pauta.builder()
                .descricao("Votacao Teste")
                .titulo("Votacao 1")
                .id(0L)
                .votacaoAberta(false)
                .build();
    }

    public static PautaDTO getPautaDTO() {
        return PautaDTO.builder().descricao("Votacao Teste").titulo("Votacao 1").votacaoAberta(false).build();
    }

    public static Voto getVoto() {
        return Voto.builder().pauta(getPauta()).id(1L).opcao("S").associado(new Associado()).build();
    }
}
