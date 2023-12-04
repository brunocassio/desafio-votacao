package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.dto.AssociadoDTO;
import com.sicredi.desafiovotacao.model.Associado;
import org.springframework.stereotype.Service;

@Service
public interface AssociadoService {

    Associado incluir(AssociadoDTO dto);
    Associado buscarAssociadoById(Long id);
}
