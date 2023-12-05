package com.sicredi.desafiovotacao.controller;

import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.model.Associado;
import com.sicredi.desafiovotacao.model.Pauta;
import com.sicredi.desafiovotacao.model.Voto;
import com.sicredi.desafiovotacao.service.AssociadoService;
import com.sicredi.desafiovotacao.service.PautaService;
import com.sicredi.desafiovotacao.service.VotoService;
import com.sicredi.desafiovotacao.util.Responses;
import com.sicredi.desafiovotacao.util.Util;
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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PautaControllerTest {
    private static final Long ASSOCIADO_ID = 1L;
    private static final String VOTO = "S";
    @InjectMocks
    private PautaController pautaController;
    @Mock
    private PautaService pautaService;
    @Mock
    private VotoService votoService;
    @Mock
    private AssociadoService associadoService;
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pautaController).build();
    }
    @Test
    public void testCadastrarPauta() throws Exception {
        Mockito.when(pautaService.inserir(Mockito.any(PautaDTO.class))).thenReturn(Util.getPauta());

        MvcResult result = mockMvc
                .perform(post("/v1/pauta").content(Responses.EXPECTED_INSERT_PAUTA_REPONSE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        assertEquals(Responses.EXPECTED_INSERT_PAUTA_REPONSE, resp);
    }

    @Test
    public void testRegistrarVoto() throws Exception {
        Pauta pautaVotacaoAberta = Util.getPauta();
        pautaVotacaoAberta.setVotacaoAberta(true);
        Mockito.when(pautaService.buscarPauta(Mockito.anyLong())).thenReturn(pautaVotacaoAberta);
        Mockito.when(votoService.buscarVotosPorPauta(Mockito.anyLong())).thenReturn(Arrays.asList(Util.getVoto()));
        Mockito.when(associadoService.buscarAssociadoById(Mockito.anyLong())).thenReturn(new Associado());

        MvcResult result = mockMvc
                .perform(post("/v1/pauta/{id}/registrar-voto", 1)
                        .param("associadoId", ASSOCIADO_ID.toString())
                        .param("voto", VOTO)
                        .content(Responses.EXPECTED_REGISTRAR_VOTO_REPONSE).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        String resp = result.getResponse().getContentAsString();
        assertEquals(Responses.EXPECTED_REGISTRAR_VOTO_REPONSE, resp);

    }



}
