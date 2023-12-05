package com.sicredi.desafiovotacao.util;

public abstract class Responses {

    public static final String EXPECTED_INSERT_REPONSE = "{\"id\":0,\"nome\":\"Bruno\",\"cpf\":\"01234567891\",\"votos\":null}";
    public static final String EXPECTED_INSERT_PAUTA_REPONSE = "{\"id\":0,\"titulo\":\"Votacao 1\",\"descricao\":\"Votacao Teste\",\"votacaoAberta\":false,\"votos\":null}";
    public static final String EXPECTED_REGISTRAR_VOTO_REPONSE = "Voto registrado com sucesso para a pauta: Votacao 1";
}
