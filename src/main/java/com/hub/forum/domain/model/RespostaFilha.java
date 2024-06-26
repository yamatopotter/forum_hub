package com.hub.forum.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="RespostaFilha")
@Table(name = "respostas_filhas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
