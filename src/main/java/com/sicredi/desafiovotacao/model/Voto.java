package com.sicredi.desafiovotacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String opcao;
    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;
    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;
}
