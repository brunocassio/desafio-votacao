package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.model.Voto;
import com.sicredi.desafiovotacao.repository.VotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoServiceImpl implements VotoService{

    private final VotoRepository votoRepository;

    public VotoServiceImpl(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    public List<Voto> buscarVotosPorPauta(Long idPauta) {
        return votoRepository.findByPautaId(idPauta);
    }

    @Override
    public void registrarVoto(Voto voto) {
        votoRepository.save(voto);
    }
}
