package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.model.Voto;
import com.sicredi.desafiovotacao.repository.VotoRepository;
import com.sicredi.desafiovotacao.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class VotoServiceImplTest {

    @InjectMocks
    private VotoServiceImpl votoService;
    @Mock
    private VotoRepository votoRepository;
    @Test
    public void testBuscarVotosPorPauta() {
        Mockito.when(votoRepository.findByPautaId(Mockito.anyLong())).thenReturn(Arrays.asList(Util.getVoto()));
        List<Voto> votos = votoService.buscarVotosPorPauta(Mockito.anyLong());
        Assertions.assertEquals(Util.getPauta().getId(), votos.get(0).getPauta().getId());
        Assertions.assertEquals(1, votos.size());
    }
}
