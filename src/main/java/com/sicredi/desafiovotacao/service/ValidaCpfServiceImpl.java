package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.dto.CpfValidoDTO;
import org.springframework.stereotype.Service;

@Service
public class ValidaCpfServiceImpl implements ValidaCpfService{
    @Override
    public CpfValidoDTO validarCpf(String cpf) {
       if (isValid(cpf)) {
           return CpfValidoDTO.builder().status("ABLE_TO_VOTE").build();
       } else {
           return CpfValidoDTO.builder().status("UNABLE_TO_VOTE").build();
       }
    }

    public static boolean isValid(String cpf) {
        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Calcular os dígitos verificadores
        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += digitos[i] * (10 - i);
        }

        int resto1 = soma1 % 11;
        int digitoVerificador1 = (resto1 < 2) ? 0 : 11 - resto1;

        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += digitos[i] * (11 - i);
        }

        int resto2 = soma2 % 11;
        int digitoVerificador2 = (resto2 < 2) ? 0 : 11 - resto2;

        // Verificar se os dígitos verificadores calculados correspondem aos dígitos reais
        return (digitoVerificador1 == digitos[9] && digitoVerificador2 == digitos[10]);
    }
}




