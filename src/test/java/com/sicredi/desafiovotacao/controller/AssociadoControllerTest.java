package com.sicredi.desafiovotacao.controller;

import com.sicredi.desafiovotacao.dto.AssociadoDTO;
import com.sicredi.desafiovotacao.dto.CpfValidoDTO;
import com.sicredi.desafiovotacao.model.Associado;
import com.sicredi.desafiovotacao.service.AssociadoService;
import com.sicredi.desafiovotacao.service.ValidaCpfService;
import com.sicredi.desafiovotacao.util.Responses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AssociadoControllerTest {

    @InjectMocks
    private AssociadoController associadoController;
    @Mock
    private AssociadoService associadoService;

    @Mock
    private ValidaCpfService validaCpfService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(associadoController).build();
    }

    @Test
    public void testarIncluirAssociado() throws Exception {
        Associado entity = Associado.builder().nome("Bruno").cpf("01234567891").id(0).build();

        Mockito.when(associadoService.incluir(Mockito.any(AssociadoDTO.class))).thenReturn(entity);
        Mockito.when(validaCpfService.validarCpf(Mockito.anyString())).thenReturn(CpfValidoDTO.builder().status("ABLE_TO_VOTE").build());

        MvcResult result = mockMvc
                .perform(post("/associado").content(Responses.EXPECTED_INSERT_REPONSE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        assertEquals(Responses.EXPECTED_INSERT_REPONSE, resp);
    }
}
