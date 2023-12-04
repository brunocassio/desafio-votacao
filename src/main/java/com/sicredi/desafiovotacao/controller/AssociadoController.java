package com.sicredi.desafiovotacao.controller;

import com.sicredi.desafiovotacao.dto.AssociadoDTO;
import com.sicredi.desafiovotacao.dto.CpfValidoDTO;
import com.sicredi.desafiovotacao.exception.CpfValidationException;
import com.sicredi.desafiovotacao.model.Associado;
import com.sicredi.desafiovotacao.service.AssociadoService;
import com.sicredi.desafiovotacao.service.ValidaCpfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/associado")
public class AssociadoController {

    private final AssociadoService associadoService;
    private final ValidaCpfService validaCpfService;

    public AssociadoController(AssociadoService associadoService, ValidaCpfService validaCpfService) {
        this.associadoService = associadoService;
        this.validaCpfService = validaCpfService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Associado> incluirAssociado(@RequestBody AssociadoDTO dto) {
        CpfValidoDTO cpfValido = validaCpfService.validarCpf(dto.getCpf());
        if (cpfValido.getStatus().equals("ABLE_TO_VOTE")) {
            return ResponseEntity.ok(associadoService.incluir(dto));
        } else {
            throw new CpfValidationException();
        }
    }

}
