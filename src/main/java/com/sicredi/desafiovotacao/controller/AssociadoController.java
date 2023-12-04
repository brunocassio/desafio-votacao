package com.sicredi.desafiovotacao.controller;

import com.sicredi.desafiovotacao.dto.AssociadoDTO;
import com.sicredi.desafiovotacao.model.Associado;
import com.sicredi.desafiovotacao.service.AssociadoService;
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

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Associado> incluirAssociado(@RequestBody AssociadoDTO dto) {
        return ResponseEntity.ok(associadoService.incluir(dto));
    }

}
