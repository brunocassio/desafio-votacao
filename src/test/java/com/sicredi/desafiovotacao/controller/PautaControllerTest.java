package com.sicredi.desafiovotacao.controller;

import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.model.Pauta;
import com.sicredi.desafiovotacao.service.PautaService;
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
public class PautaControllerTest {
    @InjectMocks
    private PautaController pautaController;
    @Mock
    private PautaService pautaService;
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pautaController).build();
    }
    @Test
    public void testCadastrarPauta() throws Exception {
        Pauta entity = Pauta.builder()
                .descricao("Votacao Teste")
                .titulo("Votacao 1")
                .id(0L)
                .votacaoAberta(false)
                .build();

        Mockito.when(pautaService.inserir(Mockito.any(PautaDTO.class))).thenReturn(entity);

        MvcResult result = mockMvc
                .perform(post("/pauta").content(Responses.EXPECTED_INSERT_PAUTA_REPONSE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        assertEquals(Responses.EXPECTED_INSERT_PAUTA_REPONSE, resp);
    }
}
