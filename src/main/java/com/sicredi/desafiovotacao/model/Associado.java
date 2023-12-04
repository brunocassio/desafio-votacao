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
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cpf;
    @OneToMany(mappedBy = "associado")
    private List<Voto> votos;

}
