package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.converter.PautaConverter;
import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.model.Pauta;
import com.sicredi.desafiovotacao.repository.PautaRepository;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService{

    private final PautaRepository pautaRepository;

    public PautaServiceImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta inserir(PautaDTO dto) {
        return pautaRepository.save(PautaConverter.convertToEntity(dto));
    }

    @Override
    public Pauta buscarPauta(Long id) {
        return pautaRepository.findById(id).get();
    }

    @Override
    public void editarPauta(Pauta pauta) {
        pautaRepository.save(pauta);
    }
}
