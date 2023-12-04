package com.sicredi.desafiovotacao.repository;

import com.sicredi.desafiovotacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findByAssociadoId(Long associadoId);
    List<Voto> findByPautaId(Long pautaId);
}
