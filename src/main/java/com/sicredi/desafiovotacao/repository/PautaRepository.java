package com.sicredi.desafiovotacao.repository;

import com.sicredi.desafiovotacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
