package com.sicredi.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;
    private boolean votacaoAberta;
    @OneToMany(mappedBy = "pauta")
    private List<Voto> votos;
}
