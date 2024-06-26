package com.hub.forum.domain.model;

import com.hub.forum.domain.DTO.Curso.UpdateDataCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Curso")
@Table(name="cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String categoria;
    private Boolean ativo;

    public void delete() {
        this.ativo = false;
    }

    public void update(UpdateDataCurso curso) {
        if(curso.nome() != null){
            this.nome = curso.nome();
        }

        if(curso.categoria() != null){
            this.categoria = curso.categoria();
        }

        if(curso.ativo() != null){
            this.ativo = curso.ativo();
        }
    }
}
