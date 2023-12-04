package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.converter.AssociadoConverter;
import com.sicredi.desafiovotacao.dto.AssociadoDTO;
import com.sicredi.desafiovotacao.model.Associado;
import com.sicredi.desafiovotacao.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociadoServiceImpl implements AssociadoService{

    private final AssociadoRepository associadoRepository;

    public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    public Associado incluir(AssociadoDTO dto) {
        return associadoRepository.save(AssociadoConverter.convertToEntity(dto));
    }

    @Override
    public Associado buscarAssociadoById(Long id) {
        return associadoRepository.findById(id).get();
    }
}
