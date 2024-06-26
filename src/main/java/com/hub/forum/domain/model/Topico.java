package com.hub.forum.domain.model;

import com.hub.forum.domain.DTO.Topico.UpdateDataTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="Topico")
@Table(name="topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resposta> respostas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="solucao_id")
    private Resposta solucao;
    private Boolean ativo;

    public void excluir(){
        this.ativo = false;
    }

    public void update(UpdateDataTopico topico, Curso curso) {
        if(topico.titulo() != null){
            this.titulo = topico.titulo();
        }

        if(topico.mensagem() != null){
            this.mensagem = topico.mensagem();
        }

        if(curso != null){
            this.curso = curso;
        }
    }

    public void isSolucao(Resposta resposta) {
        this.solucao = resposta;
        this.status = Status.SOLVED;
    }

    public void closeTopico(){
        this.status = Status.CLOSED;
    }
}
