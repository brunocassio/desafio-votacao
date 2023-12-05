package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.converter.PautaConverter;
import com.sicredi.desafiovotacao.model.Pauta;
import com.sicredi.desafiovotacao.repository.PautaRepository;
import com.sicredi.desafiovotacao.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static com.sicredi.desafiovotacao.util.Util.*;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {

    @InjectMocks
    private PautaServiceImpl pautaService;
    @Mock
    private PautaRepository pautaRepository;

    @Test
    public void inserir() {
        Mockito.when(pautaRepository.save(PautaConverter.convertToEntity(getPautaDTO()))).thenReturn(getPauta());
        Pauta pauta = pautaService.inserir(getPautaDTO());

        Assertions.assertEquals("Votacao Teste", pauta.getDescricao());
        Assertions.assertEquals("Votacao 1", pauta.getTitulo());
        Assertions.assertEquals(0l, pauta.getId());
        Assertions.assertEquals(false, pauta.isVotacaoAberta());
    }
}
