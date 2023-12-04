package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.model.Pauta;
import org.springframework.stereotype.Service;

@Service
public interface PautaService {

    Pauta inserir(PautaDTO dto);
    Pauta buscarPauta(Long id);
    void editarPauta(Pauta pauta);
}
