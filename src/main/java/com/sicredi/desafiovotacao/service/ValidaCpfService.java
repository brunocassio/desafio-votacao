package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.dto.CpfValidoDTO;
import org.springframework.stereotype.Service;

@Service
public interface ValidaCpfService {

    CpfValidoDTO validarCpf(String cpf);
}
