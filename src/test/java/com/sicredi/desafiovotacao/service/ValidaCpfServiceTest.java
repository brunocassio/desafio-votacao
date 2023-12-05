package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.dto.CpfValidoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidaCpfServiceTest {

    private final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
    private final String CPF_VALIDO = "26348454215";
    private final String CPF_INVALIDO = "000";

    @InjectMocks
    private ValidaCpfServiceImpl validaCpfService;

    @Test
    public void testCpfValido() {
        CpfValidoDTO cpfValidoDTO = validaCpfService.validarCpf(CPF_VALIDO);
        Assertions.assertEquals(ABLE_TO_VOTE, cpfValidoDTO.getStatus());
    }

    @Test
    public void testCpfInvalido() {
        CpfValidoDTO cpfValidoDTO = validaCpfService.validarCpf(CPF_INVALIDO);
        Assertions.assertEquals(UNABLE_TO_VOTE, cpfValidoDTO.getStatus());
    }
}
