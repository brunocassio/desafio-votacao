package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.converter.AssociadoConverter;
import com.sicredi.desafiovotacao.dto.CpfValidoDTO;
import com.sicredi.desafiovotacao.model.Associado;
import com.sicredi.desafiovotacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTest {

    @InjectMocks
    private AssociadoServiceImpl associadoService;
    @Mock
    private AssociadoRepository associadoRepository;
    @Mock
    private ValidaCpfServiceImpl validaCpfService;
    @Test
    public void testIncluir() {
        Associado entity = Associado.builder().nome("Bruno").cpf("01234567891").id(0).build();

        Mockito.when(associadoRepository.save(entity)).thenReturn(entity);
        Associado associado = associadoService.incluir(AssociadoConverter.convertToDTO(entity));

        Assertions.assertEquals("Bruno", associado.getNome());
        Assertions.assertEquals("01234567891", associado.getCpf());
        Assertions.assertEquals(0, associado.getId());
    }
}
