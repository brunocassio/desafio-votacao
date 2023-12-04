package com.sicredi.desafiovotacao.exception;

import com.sicredi.desafiovotacao.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.sicredi.desafiovotacao.controller")
public class AssociadoControllerAdvice {

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
}
