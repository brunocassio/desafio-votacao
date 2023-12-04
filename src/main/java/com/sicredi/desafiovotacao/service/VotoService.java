package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.model.Voto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VotoService {

    List<Voto> buscarVotosPorPauta(Long idPauta);
    void registrarVoto(Voto voto);

}
