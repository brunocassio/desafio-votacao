package com.sicredi.desafiovotacao.exception;

import com.sicredi.desafiovotacao.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.sicredi.desafiovotacao.controller")
public class VotacaoControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CpfValidationException.class)
    public ErrorDTO handleCpfValidationException(CpfValidationException ex) {
        return ErrorDTO.builder()
                .message("CPF não está apto para voto: UNABLE_TO_VOTE")
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PautaNotFoundException.class)
    public ErrorDTO handlePautaNotFoundException(PautaNotFoundException ex) {
        return ErrorDTO.builder()
                .message("Pauta inexistente!")
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AssociadoNotFoundException.class)
    public ErrorDTO handleAssociadoNotFoundException(AssociadoNotFoundException ex) {
        return ErrorDTO.builder()
                .message("Associado não encontrado na base de dados!")
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(VotoInvalidoException.class)
    public ErrorDTO handleVotoInvalidoException(VotoInvalidoException ex) {
        return ErrorDTO.builder()
                .message("Formato do voto inválido! Os valores devem ser S ou N.")
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
