package com.hub.forum.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity(name="RespostaFilha")
@Table(name = "respostas_filhas")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RespostaFilha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resposta_pai_id")
    private Resposta respostaPai;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resposta_filha_id")
    private Resposta respostaFilha;
}
