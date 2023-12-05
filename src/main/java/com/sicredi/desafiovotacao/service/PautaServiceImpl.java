package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.converter.PautaConverter;
import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.exception.PautaNotFoundException;
import com.sicredi.desafiovotacao.model.Pauta;
import com.sicredi.desafiovotacao.repository.PautaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return checarSePautaExiste(pautaRepository.findById(id));
    }

    private Pauta checarSePautaExiste(Optional<Pauta> pauta) {
        if (pauta.isPresent()) {
            return pauta.get();
        } else throw new PautaNotFoundException();
    }

    @Override
    public void editarPauta(Pauta pauta) {
        pautaRepository.save(pauta);
    }
}
